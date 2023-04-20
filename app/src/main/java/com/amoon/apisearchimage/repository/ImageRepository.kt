package com.amoon.apisearchimage.repository

import com.amoon.apisearchimage.domain.model.ImageDb

interface ImageRepository {

    suspend fun search(page: Int, query: String): List<ImageDb>

}