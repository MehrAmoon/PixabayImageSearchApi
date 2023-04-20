package com.amoon.apisearchimage.network.response

import com.amoon.apisearchimage.network.model.ImagesHitsDto
import com.google.gson.annotations.SerializedName

data class ImagesResponse(

    @SerializedName("totalHits")
    var totalHits: Int,

    @SerializedName("total")
    var total: Int,

    @SerializedName("hits")
    var imagesList: List<ImagesHitsDto>,
)