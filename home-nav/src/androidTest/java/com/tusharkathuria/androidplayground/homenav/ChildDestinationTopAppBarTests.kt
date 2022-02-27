package com.tusharkathuria.androidplayground.homenav

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tusharkathuria.androidplayground.homenav.models.Destination
import com.tusharkathuria.androidplayground.homenav.ui.ChildDestinationTopBar

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class ChildDestinationTopBarTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleDisplayed() {
        val title = stringResource(Destination.Feed.titleResource)
        composeTestRule.setContent {
            ChildDestinationTopBar(
                onNavigateUp = {},
                title = title
            )
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun backIconDisplayed() {
        val title = stringResource(Destination.Feed.titleResource)
        composeTestRule.setContent {
            ChildDestinationTopBar(
                onNavigateUp = {},
                title = title
            )
        }
        composeTestRule.onNodeWithContentDescription(stringResource(R.string.back)).assertIsDisplayed()
    }

    @Test
    fun backIconTriggersCallback() {
        val title = stringResource(Destination.Feed.titleResource)
        val onNavigateUp: () -> Unit = mock()
        composeTestRule.setContent {
            ChildDestinationTopBar(
                onNavigateUp = onNavigateUp,
                title = title
            )
        }
        composeTestRule.onNodeWithContentDescription(stringResource(R.string.back)).performClick()
        verify(onNavigateUp).invoke()
    }
}