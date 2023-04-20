package com.amoon.apisearchimage.cache


import androidx.room.*
import com.amoon.apisearchimage.domain.model.ImageDb
import java.util.*

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageDb>)

    @Query("SELECT * FROM images WHERE query LIKE '%' || :query || '%'")
    suspend fun getAllImages(query: String): List<ImageDb>
}