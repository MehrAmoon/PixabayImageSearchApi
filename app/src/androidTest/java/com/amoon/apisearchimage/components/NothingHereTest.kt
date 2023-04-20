package com.amoon.apisearchimage.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.amoon.apisearchimage.presentation.components.NothingHere
import org.junit.Rule
import org.junit.Test

class NothingHereTest {


        @get:Rule
        val rule = createComposeRule()

        @Test
        fun nothingHereTest() {
            rule.setContent {
                NothingHere()
            }
            rule.onNodeWithText("¯\\_(ツ)_/¯").assertIsDisplayed()
            rule.onNodeWithText("There's nothing here").assertIsDisplayed()
        }


}