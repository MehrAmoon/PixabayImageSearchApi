package com.amoon.apisearchimage.presentation.ui.imagesDetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.presentation.ui.imagesDetail.ImagesDetailStateEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


const val STATE_KEY_IMAGE = "image.state.image.key"

@ExperimentalCoroutinesApi
@HiltViewModel
class ImagesDetailViewModel
@Inject
constructor(
    state: SavedStateHandle,
): ViewModel(){

    val imageDetail: MutableState<ImageDb?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {
        // restore if process dies
        state.get<ImageDb>(STATE_KEY_IMAGE)?.let{ imageDetails ->
            onTriggerEvent(GetImageDetailStateEvent(imageDetails))
        }
    }

    fun onTriggerEvent(event: ImagesDetailStateEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is GetImageDetailStateEvent -> {
                            getImage(event.imageDetail)
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private suspend fun getImage(imageDetail: ImageDb) {
        loading.value = true

        // simulate a delay to show loading
        delay(1000)
        this.imageDetail.value = imageDetail
        loading.value = false
    }

}