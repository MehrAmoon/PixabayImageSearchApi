package com.amoon.apisearchimage.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import com.amoon.apisearchimage.presentation.components.DefaultSnackbar
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


@ExperimentalMaterialApi
@Composable
fun DefaultSnackbarTestWrapper(onDismiss: () -> Unit): SnackbarHostState {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarHostState) {
        snackbarHostState.showSnackbar(
            message = "Test message",
            actionLabel = "Dismiss",
            duration = SnackbarDuration.Long
        )
    }

    DefaultSnackbar(snackbarHostState = snackbarHostState, onDismiss = onDismiss)

    return snackbarHostState
}

@ExperimentalMaterialApi
class DefaultSnackbarTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testSnackbar() {
        var isDismissed = false

        rule.setContent {
            val snackbarHostState = DefaultSnackbarTestWrapper { isDismissed = true }

            if (isDismissed) {
                Assert.assertFalse(snackbarHostState.currentSnackbarData!!.dismiss().equals(true))
                snackbarHostState.currentSnackbarData!!.dismiss()
            }
        }

        rule.waitForIdle()

        Assert.assertTrue(!isDismissed)
    }
}