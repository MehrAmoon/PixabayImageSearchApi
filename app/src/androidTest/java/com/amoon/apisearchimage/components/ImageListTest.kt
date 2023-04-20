package com.amoon.apisearchimage.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.amoon.apisearchimage.base.BaseData.mockResults
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.presentation.components.ImageList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class ImageListTest {


    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @Composable
    fun testImageList(
        loading: Boolean,
        images: List<ImageDb>,
        onChangeScrollPosition: (Int) -> Unit,
        page: Int,
        onTriggerNextPage: () -> Unit,
        onNavigateToImageDetailScreen: (ImageDb) -> Unit,
    ) {
        MaterialTheme {
            Surface(color = MaterialTheme.colors.background) {
                ImageList(
                    loading = loading,
                    images = images,
                    onChangeScrollPosition = onChangeScrollPosition,
                    page = page,
                    onTriggerNextPage = onTriggerNextPage,
                    onNavigateToImageDetailScreen = onNavigateToImageDetailScreen,
                )
            }
        }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @get:Rule
    val rule = createComposeRule()


    @OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun testImageList() {
        // Add some dummy images to use in the list
        val images = listOf(mockResults)

        // Launch the composable with the test data
        rule.setContent {
            testImageList(
                loading = false,
                images = images,
                onChangeScrollPosition = {},
                page = 1,
                onTriggerNextPage = {},
                onNavigateToImageDetailScreen = {},
            )
        }

        // Verify that the ImageCards are displayed and contain the correct data
        images.forEachIndexed { index, image ->
            rule.onNodeWithText("M3hr", substring = true).assertExists()
            rule.onNodeWithContentDescription("Images").assertExists()
            rule.onNodeWithContentDescription("Images").performClick()
        }
    }


}