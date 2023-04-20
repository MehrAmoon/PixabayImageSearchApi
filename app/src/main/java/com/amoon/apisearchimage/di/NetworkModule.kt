package com.amoon.apisearchimage.di

import com.amoon.apisearchimage.BuildConfig
import com.amoon.apisearchimage.network.RetroService
import com.amoon.apisearchimage.network.model.ImagesDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideImagesMapper(): ImagesDtoMapper {
        return ImagesDtoMapper()
    }

    @Singleton
    @Provides
    fun provideImagesService(): RetroService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetroService::class.java)
    }

}