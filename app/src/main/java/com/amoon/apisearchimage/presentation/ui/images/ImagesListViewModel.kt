package com.amoon.apisearchimage.presentation.ui.images

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.repository.ImageRepository
import com.amoon.apisearchimage.presentation.ui.images.ImagesListEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 10
const val STATE_KEY_PAGE = "image.state.page.key"
const val STATE_KEY_QUERY = "image.state.query.key"
const val STATE_KEY_LIST_POSITION = "image.state.query.list_position"


@HiltViewModel
class ImagesListViewModel
@Inject
constructor(
    private val repository: ImageRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    val imagesListItem: MutableState<List<ImageDb>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("fruits")

    val loading = mutableStateOf(false)

    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableStateOf(1)

    var imageListScrollPosition = 0


    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }


        if(imageListScrollPosition != 0){
            onTriggerEvent(RestoreStateEvent)
        }
        else{
            onTriggerEvent(NewSearchEvent)
        }

    }

    fun onTriggerEvent(event: ImagesListEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    suspend fun restoreState(){
        loading.value = true
        val results: MutableList<ImageDb> = mutableListOf()
        for(p in 1..page.value){
            val result = repository.search(
                page = p,
                query = query.value
            )
            results.addAll(result)
            if(p == page.value){ // done
                imagesListItem.value = results
                loading.value = false
            }
        }
    }

    private suspend fun newSearch() {
        loading.value = true
        resetSearchState()
        delay(2000)
        val result = repository.search(
            page = 1,
            query = query.value
        )
        imagesListItem.value = result
        loading.value = false
    }

    private suspend fun nextPage(){
        // prevent duplicate event due to recompose happening to quickly
        if((imageListScrollPosition + 1) >= (page.value * PAGE_SIZE) ){
            loading.value = true
            incrementPage()

            if(page.value > 1){
                val result = repository.search(page = page.value, query = query.value )
                appendImages(result)
            }
            loading.value = false
        }
    }

    /**
     * Append new images to the current list of image
     */
    private fun appendImages(images: List<ImageDb>){
        val current = ArrayList(this.imagesListItem.value)
        current.addAll(images)
        this.imagesListItem.value = current
    }

    private fun incrementPage(){
        setPage(page.value + 1)
    }

    fun onChangeImageScrollPosition(position: Int){
        setListScrollPosition(position = position)
    }

    /**
     * Called when a new search is executed.
     */
    private fun resetSearchState() {
        imagesListItem.value = listOf()
        page.value = 1
        onChangeImageScrollPosition(0)
    }


    fun onQueryChanged(query: String) {
        setQuery(query)
    }


    private fun setListScrollPosition(position: Int){
        imageListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int){
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }


    private fun setQuery(query: String){
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }

}