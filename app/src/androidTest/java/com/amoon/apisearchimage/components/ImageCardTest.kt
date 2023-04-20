package com.amoon.apisearchimage.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.amoon.apisearchimage.base.BaseData.mockResults
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.presentation.components.ImageCard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class ImageCardTest {



    @get:Rule
    val rule = createComposeRule()

    @ExperimentalComposeUiApi
    @ExperimentalCoroutinesApi
    @Test
    fun imageCardRendersCorrectly() {
        // Define test data
        val image = mockResults

        // Render the composable
        rule.setContent {
            ImageCard(
                images = image,
                onClick = { /* do nothing */ }
            )
        }

        // Assert that the composable renders correctly
        rule.onNodeWithContentDescription("Images").assertExists()
        rule.onNodeWithText("M3hr", substring = true).assertExists()
        rule.onNodeWithText("blossom, bloom, flower", substring = true).assertExists()
    }

}