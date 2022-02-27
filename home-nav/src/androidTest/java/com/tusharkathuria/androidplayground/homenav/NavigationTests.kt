package com.tusharkathuria.androidplayground.homenav

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tusharkathuria.androidplayground.homenav.models.Destination
import com.tusharkathuria.androidplayground.homenav.ui.Navigation
import org.junit.Rule
import org.junit.Test

class NavigationTests {
    @get: Rule
    val composeTestRule = createComposeRule()

    private fun assertNavigation(destination: Destination) {
        composeTestRule.setContent {
            val navController: NavHostController = rememberNavController()
            Navigation(navHostController = navController)
            navController.navigate(destination.path)
        }
        composeTestRule.onNodeWithTag(destination.path).assertIsDisplayed()
    }

    @Test
    fun feedDisplayedByDefault() {
        composeTestRule.setContent {
            Navigation(navHostController = rememberNavController())
        }
        composeTestRule.onNodeWithTag(Destination.Feed.path).assertIsDisplayed()
    }

    @Test
    fun contactsDisplayed() {
        assertNavigation(Destination.Contacts)
    }
    @Test
    fun calendarDisplayed() {
        assertNavigation(Destination.Calendar)
    }
    @Test
    fun createDisplayed() {
        assertNavigation(Destination.Add)
    }
    @Test
    fun upgradeDisplayed() {
        assertNavigation(Destination.Upgrade)
    }
    @Test
    fun settingsDisplayed() {
        assertNavigation(Destination.Settings)
    }
}