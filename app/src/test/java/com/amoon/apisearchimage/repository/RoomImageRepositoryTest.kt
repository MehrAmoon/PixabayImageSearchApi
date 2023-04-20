package com.amoon.apisearchimage.repository

import com.amoon.apisearchimage.cache.ImagesDao
import com.amoon.apisearchimage.domain.model.ImageDb
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RoomImageRepositoryTest {

    @Mock
    private lateinit var imagesDao: ImagesDao

    private lateinit var roomImageRepository: RoomImageRepository

    @Before
    fun setUp() {
        roomImageRepository = RoomImageRepository(imagesDao)
    }

    @Test
    fun `saveImages should call insertImages in imagesDao`() = runBlockingTest {
        val images = listOf(
            ImageDb(195890, "cats", "https://pixabay.com/en/blossom-bloom-flower-195893/", "flower", "blossom, bloom, flower", "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg",
            150, 84, "https://pixabay.com/get/35bbf209e13e39d2_640.jpg", 640, 360, "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a9364a9fd0a76647.jpg",
            400, 2250, 4731420, 7671, 6439, 5, 2, 48777, "Josch13", "https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg"))
        roomImageRepository.saveImages(images)
        verify(imagesDao).insertImages(images)
    }
}
