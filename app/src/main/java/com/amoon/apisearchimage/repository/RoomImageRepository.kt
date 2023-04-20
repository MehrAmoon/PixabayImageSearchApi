package com.amoon.apisearchimage.repository

import com.amoon.apisearchimage.cache.ImagesDao
import com.amoon.apisearchimage.domain.model.ImageDb
import javax.inject.Inject

class RoomImageRepository @Inject constructor(private val imagesDao: ImagesDao) : ImageRepository {

    suspend fun saveImages(images: List<ImageDb>) {
        imagesDao.insertImages(images)
    }

    override suspend fun search(page: Int, query: String): List<ImageDb> {
        return imagesDao.getAllImages(query)
    }
}