package com.amoon.apisearchimage.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.amoon.apisearchimage.base.BaseData.mockResults
import com.amoon.apisearchimage.presentation.components.ImagesView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class ImagesViewTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val rule = createComposeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testImagesView() {
        // Set up test data
        val image = mockResults

        // Launch the composable
        rule.setContent {
            ImagesView(image)
        }


        // Verify the image tags are displayed
        rule.onNodeWithText("blossom, bloom, flower", substring = true).assertExists()

        // Verify the user name is displayed
        rule.onNodeWithText("M3hr").assertExists()

        // Verify the comment count is displayed
        rule.onNodeWithText("2").assertExists()

        // Verify the download count is displayed
        rule.onNodeWithText("6439").assertExists()

        // Verify the like count is displayed
        rule.onNodeWithText("5").assertExists()
    }

}