package com.amoon.apisearchimage.repository

import com.amoon.apisearchimage.BuildConfig
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.network.RetroService
import com.amoon.apisearchimage.network.model.ImagesDtoMapper

class NetworkImageRepository(
    private val imageService: RetroService,
    private val mapper: ImagesDtoMapper,
    private val roomRepository: RoomImageRepository
): ImageRepository {

    override suspend fun search(page: Int, query: String): List<ImageDb> {
         try {
            val response = mapper.toDomainList(imageService.search(
                key = BuildConfig.PIXABAY_KEY,
                page = page,
                query = query,
                perPage = 10).imagesList)

             if (response.isNotEmpty()) {
                 val imageEntities = response.map {
                     ImageDb(it.id,query,it.pageURL,
                         it.type, it.tags,it.previewURL,it.previewWidth,it.previewHeight,it.webformatURL,it.webformatWidth,it.webformatHeight,
                         it.largeImageURL,it.fullHDURL,it.imageURL,it.imageWidth,it.imageHeight,it.imageSize,it.views,it.downloads,
                         it.likes,it.comments,it.user_id,it.user,it.userImageURL)
                 }
                 roomRepository.saveImages(imageEntities)
             }
           return response
        } catch (e: Exception) {
           return roomRepository.search(page, query)
        }
    }
}