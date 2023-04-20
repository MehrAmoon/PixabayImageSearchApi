package com.amoon.apisearchimage.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amoon.apisearchimage.domain.model.ImageDb

@Database(entities = [ImageDb::class], version = 1, exportSchema = false)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao() : ImagesDao
}
