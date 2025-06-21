package com.example.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.common.AppPreferences
import core.ui.theme.Dimens
import core.ui.theme.MyComposeAppTheme
import core.ui.components.PrimaryButton

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val navigateToDashboard = state.success == true
    LaunchedEffect(navigateToDashboard) {
        if (navigateToDashboard) {
            AppPreferences.setLoggedIn(context, true)
            navController.navigate("dashboard") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    MyComposeAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Login", style = MaterialTheme.typography.headlineLarge)

            Spacer(height(Dimens.PaddingMedium))
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::onEmailChanged,
                label = { Text("Email") },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(height(Dimens.PaddingSmall))
            OutlinedTextField(
                value = state.password,
                onValueChange = viewModel::onPasswordChanged,
                label = { Text("Password") },
                visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = viewModel::togglePasswordVisibility) {
                        Icon(
                            if (state.showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                isError = state.passwordError != null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(height(Dimens.PaddingMedium))
            PrimaryButton(text = "Login", loading = state.loading) {
                viewModel.onLoginClick()
            }

            Spacer(height(Dimens.PaddingSmall))
            Text("Forgot password?", modifier = Modifier.clickable { /* Handle */ })
            Spacer(Modifier.height(4.dp))
            Text("Don't have an account? Sign Up", modifier = Modifier.clickable { /* Handle */ })
        }
    }
}
