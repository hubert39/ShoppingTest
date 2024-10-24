package com.example.shoppingapp

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick

/*
a. How do you know the test went to the next screen? What if the network connection is slow?
assert the next element which must be displayed on the next screen
can be done by using ContentDescription, or by using the testTag
We can use waitUntil() function to wait for the next screen to be loaded

b. Implement an internal method that handles this (internal fun ComposeTestRule.performClickAndWaitForNextScreen)
i. make sure that this method is available to be used on all the different screens
ii. log the screen the user is in currently right after the transition to the new screen is assured (new screen loaded)
 */
internal fun ComposeTestRule.performClickAndWaitForNextScreen(
    currentScreenContentDescription: String,
    buttonContentDescription: String,
    nextScreenContentDescription: String,
    timeoutMillis: Long = 5000
) {
    onNodeWithContentDescription(currentScreenContentDescription).assertIsDisplayed()
    Log.d("Test", "before the Click action: $currentScreenContentDescription")

    onNodeWithContentDescription(buttonContentDescription).performClick()
    waitUntil (timeoutMillis) {
        onAllNodesWithContentDescription(nextScreenContentDescription).fetchSemanticsNodes().isNotEmpty()
    }

    onNodeWithContentDescription(nextScreenContentDescription).assertIsDisplayed()
    Log.d("Test", "after the Click action: $nextScreenContentDescription")

}