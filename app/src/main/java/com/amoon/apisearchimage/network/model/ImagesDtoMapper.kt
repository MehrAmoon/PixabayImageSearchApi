package com.amoon.apisearchimage.network.model

import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.domain.util.DomainMapper

class ImagesDtoMapper : DomainMapper<ImagesHitsDto, ImageDb> {

    override fun mapToDomainModel(model: ImagesHitsDto): ImageDb {
        return ImageDb(
            id = model.id,
            query = "",
            pageURL = model.pageURL,
            type = model.type,
            tags = model.tags,
            previewURL = model.previewURL,
            previewWidth = model.previewWidth,
            previewHeight = model.previewHeight,
            webformatURL = model.webformatURL,
            webformatWidth = model.webformatWidth,
            webformatHeight = model.webformatHeight,
            largeImageURL = model.largeImageURL,
            fullHDURL = model.fullHDURL,
            imageURL = model.imageURL,
            imageWidth = model.imageWidth,
            imageHeight = model.imageHeight,
            imageSize = model.imageSize,
            views = model.views,
            downloads = model.downloads,
            likes = model.likes,
            comments = model.comments,
            user_id = model.user_id,
            user = model.user,
            userImageURL = model.userImageURL
        )
    }

    fun toDomainList(initial: List<ImagesHitsDto>): List<ImageDb> {
        return initial.map {
            mapToDomainModel(it) }
    }

}
