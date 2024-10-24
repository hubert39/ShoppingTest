package com.example.shoppingapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import com.example.shoppingapp.login.MainNavigation
import org.junit.Rule
import org.junit.Test

/*
2.a What is required before the test starts?
- Include testing dependencies in your build.gradle.
- Set up a ComposeTestRule in your test class.
- Define the Composable content to be tested using composeTestRule.setContent.
- Use contentDescription or testTag for unique element identification.
- Handle asynchronous events if needed (waitUntil, runOnIdle).
 */
class LoginToAddToCartTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginToAddToCartScreen() {
        val username = "admin"
        val password = "1234"
        val firstName = "John"
        val lastName = "Doe"
        val zipCode = "12345"

        composeTestRule.setContent {
            MaterialTheme {
                MainNavigation()
            }
        }

        //1. On the Login Screen (starting point), login with a user
        composeTestRule.onNodeWithContentDescription("Please sign in to continue").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("UsernameTextField").performTextInput(username)
        composeTestRule.onNodeWithContentDescription("PasswordTextField").performTextInput(password)
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Please sign in to continue",
            buttonContentDescription = "LoginButton",
            nextScreenContentDescription = "Products"
        )

        //2. User is now on Showcase Screen, from a list of items being shown, open the first item from the list
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Products",
            buttonContentDescription = "Product 1",
            nextScreenContentDescription = "Product Details"
        )
        //3.On the Item Screen, add this item to the cart
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Product Details",
            buttonContentDescription = "AddToCartButton",
            nextScreenContentDescription = "Shopping Cart"
        )
        //close the Shopping Cart Screen
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Shopping Cart",
            buttonContentDescription = "Close",
            nextScreenContentDescription = "Product Details"
        )
        //close the Product Details Screen
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Product Details",
            buttonContentDescription = "Close",
            nextScreenContentDescription = "Products"
        )
        //4. Go back to Showcase Screen, the list shows again, open the second item from the list
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Products",
            buttonContentDescription = "Product 2",
            nextScreenContentDescription = "Product Details"
        )
        //4.On the Item Screen, add the second item to the cart
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Product Details",
            buttonContentDescription = "AddToCartButton",
            nextScreenContentDescription = "Shopping Cart"
        )
        //5. Go to the Cart Screen and start the purchase flow (you will be taken to Address Screen)
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Shopping Cart",
            buttonContentDescription = "CheckoutButton",
            nextScreenContentDescription = "Check out your information"
        )
        //6. On Address Screen, complete the address and continue to Confirm Purchase Screen
        composeTestRule.onNodeWithContentDescription("FirstNameTextField").performTextInput(firstName)
        composeTestRule.onNodeWithContentDescription("LastNameTextField").performTextInput(lastName)
        composeTestRule.onNodeWithContentDescription("ZipCodeTextField").performTextInput(zipCode)
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Check out your information",
            buttonContentDescription = "ContinueButton",
            nextScreenContentDescription = "Check Out Overview"
        )
        //7. On Confirm Purchase Screen, finish the purchase and verify it was successful
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Check Out Overview",
            buttonContentDescription = "FinishButton",
            nextScreenContentDescription = "Thank you for your order!"
        )
        //Press "Back To Home" button to return to the Showcase Screen
        composeTestRule.performClickAndWaitForNextScreen(
            currentScreenContentDescription = "Thank you for your order!",
            buttonContentDescription = "BackToHomeButton",
            nextScreenContentDescription = "Products"
        )
    }
}
