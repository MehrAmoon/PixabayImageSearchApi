package com.amoon.apisearchimage.presentation.ui.images

sealed class ImagesListEvent {

    object NewSearchEvent : ImagesListEvent()

    object NextPageEvent : ImagesListEvent()

    object RestoreStateEvent : ImagesListEvent()
}