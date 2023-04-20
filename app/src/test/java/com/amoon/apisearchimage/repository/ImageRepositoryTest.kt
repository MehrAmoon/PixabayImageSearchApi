package com.amoon.apisearchimage.repository

import com.amoon.apisearchimage.domain.model.ImageDb
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.test.runBlockingTest
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ImageRepositoryTest {

    private val imageRepository : ImageRepository = mock()

    val mockResults = listOf(ImageDb(195890, "cats", "https://pixabay.com/en/blossom-bloom-flower-195893/", "flower", "blossom, bloom, flower", "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg",
    150, 84, "https://pixabay.com/get/35bbf209e13e39d2_640.jpg", 640, 360, "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a9364a9fd0a76647.jpg",
    400, 2250, 4731420, 7671, 6439, 5, 2, 48777, "Josch13", "https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg"),
    ImageDb(195891, "cats", "https://pixabay.com/en/blossom-bloom-flower-195893/", "flower", "blossom, bloom, flower", "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg",
    150, 84, "https://pixabay.com/get/35bbf209e13e39d2_640.jpg", 640, 360, "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg", "https://pixabay.com/get/ed6a9364a9fd0a76647.jpg",
    400, 2250, 4731420, 7671, 6439, 5, 2, 48777, "Josch13", "https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg"))

    val mockEmptyResult = listOf<ImageDb>()

    @Test
    fun `search returns list of ImageDb objects`() = runBlockingTest {
        // Given
        val expectedResults = mockResults
        `when`(imageRepository.search(1, "cats")).thenReturn(expectedResults)

        // When
        val results = imageRepository.search(1, "cats")

        // Then
        assert(results == expectedResults)
    }

    @Test
    fun `search returns nothing`() = runBlockingTest {
        // Given
        val expectedResults = mockEmptyResult
        `when`(imageRepository.search(1, "cats")).thenReturn(expectedResults)

        // When
        val results = imageRepository.search(1, "cats")

        // Then
        assert(results == expectedResults)
    }

}