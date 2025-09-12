package com.aaronchancey.myresume.presentation.developerprofile

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.aaronchancey.myresume.domain.Profile
import org.junit.Rule
import org.junit.Test

class DeveloperProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_showsLoadingIndicator() {
        composeTestRule.setContent {
            DeveloperProfileScreen(
                state = ProfileState(loading = true),
            )
        }

        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
    }

    @Test
    fun contentState_showsProfileText() {
        val profile = Profile(profileText = "Hello, I am a developer.")
        composeTestRule.setContent {
            DeveloperProfileScreen(
                state = ProfileState(loading = false, profile = profile),
            )
        }

        composeTestRule.onNodeWithText("Hello, I am a developer.").assertIsDisplayed()
    }
}
