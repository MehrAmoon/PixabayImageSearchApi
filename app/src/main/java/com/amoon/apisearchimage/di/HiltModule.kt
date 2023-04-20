package com.amoon.apisearchimage.di

import android.content.Context
import androidx.room.Room
import com.amoon.apisearchimage.cache.ImagesDao
import com.amoon.apisearchimage.cache.ImagesDatabase
import com.amoon.apisearchimage.repository.RoomImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ImagesDatabase {
        return Room.databaseBuilder(
            context,
            ImagesDatabase::class.java,
            "image"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideRoomRepository(imagesDao: ImagesDao): RoomImageRepository {
        return RoomImageRepository(imagesDao)
    }

    @Provides
    fun provideImageDao(database: ImagesDatabase): ImagesDao {
        return database.imagesDao()
    }
}

