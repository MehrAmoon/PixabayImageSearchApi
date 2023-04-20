package com.amoon.apisearchimage.di

import com.amoon.apisearchimage.network.RetroService
import com.amoon.apisearchimage.network.model.ImagesDtoMapper
import com.amoon.apisearchimage.repository.ImageRepository
import com.amoon.apisearchimage.repository.NetworkImageRepository
import com.amoon.apisearchimage.repository.RoomImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideImageRepository(
        retroService: RetroService,
        imageMapper: ImagesDtoMapper,
        room: RoomImageRepository
    ): ImageRepository{
        return NetworkImageRepository(
            imageService = retroService,
            mapper = imageMapper,
            roomRepository = room
        )
    }
}