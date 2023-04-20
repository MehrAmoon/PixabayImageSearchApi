package com.amoon.apisearchimage.presentation.ui.imagesDetail

import com.amoon.apisearchimage.domain.model.ImageDb

sealed class ImagesDetailStateEvent {

    data class GetImageDetailStateEvent(
        val imageDetail: ImageDb
    ): ImagesDetailStateEvent()
}