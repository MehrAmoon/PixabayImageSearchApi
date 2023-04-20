package com.amoon.apisearchimage.presentation.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.amoon.apisearchimage.BaseApplication
import com.amoon.apisearchimage.R
import com.amoon.apisearchimage.presentation.components.ImageList
import com.amoon.apisearchimage.presentation.components.SearchBar
import com.amoon.apisearchimage.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ImagesListFargment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: ImagesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val images = viewModel.imagesListItem.value

                val query = viewModel.query.value

                val loading = viewModel.loading.value

                val page = viewModel.page.value

                val scaffoldState = rememberScaffoldState()

                AppTheme(
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState,
                    darkTheme = application.isDark.value,
                ) {

                    Scaffold(
                        topBar = {
                            SearchBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {

                                    viewModel.onTriggerEvent(ImagesListEvent.NewSearchEvent)
                                },
                                onToggleTheme = application::toggleLightTheme
                            )
                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        },

                        ) {
                        ImageList(
                            loading = loading,
                            images = images,
                            onChangeScrollPosition = viewModel::onChangeImageScrollPosition,
                            page = page,
                            onTriggerNextPage = { viewModel.onTriggerEvent(ImagesListEvent.NextPageEvent) },
                            onNavigateToImageDetailScreen = {
                                val bundle = Bundle()
                                bundle.putParcelable("imageDetails", it)
                                findNavController().navigate(R.id.viewImage, bundle)
                            }
                        )
                    }
                }
            }
        }
    }
}