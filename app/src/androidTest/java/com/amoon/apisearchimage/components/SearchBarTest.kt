package com.amoon.apisearchimage.components


import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.amoon.apisearchimage.presentation.components.SearchBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val rule = createComposeRule()

    @OptIn(ExperimentalComposeUiApi::class)
    @ExperimentalComposeUiApi
    @ExperimentalCoroutinesApi
    @Test
    fun searchBar_searchAndToggleTheme() {
        rule.setContent {
            SearchBar(query = "cat", onQueryChanged = {}, onExecuteSearch = {}, onToggleTheme = {})
        }

        // Find the text field using its label.
        val queryTextField = rule.onNodeWithText("Search")

        // Enter some text into the text field.
        queryTextField.performTextInput("cat")

        // Verify that the text field now contains the expected text.
        queryTextField.assertTextContains("cat")

    rule.onNodeWithContentDescription("Toggle Dark/Light Theme").performClick()
}
}