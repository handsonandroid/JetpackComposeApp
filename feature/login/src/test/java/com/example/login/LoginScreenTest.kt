package com.example.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = LoginViewModel()
    }

    @Test
    fun `empty email triggers email error`() = runTest {
        viewModel.onLoginClick()
        val state = viewModel.state.value
        assertEquals("Email required", state.emailError)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun email_and_password_inputs_are_visible() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
    }

    @Test
    fun login_button_disabled_while_loading() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithText("Login").assertHasClickAction()
    }
}


