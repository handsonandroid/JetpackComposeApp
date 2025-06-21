package com.example.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.common.AppPreferences
import com.example.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(2000)
        val isFirstLaunch = AppPreferences.isFirstLaunch(context)
        val isLoggedIn = AppPreferences.isLoggedIn(context)
        when {
            isFirstLaunch -> navController.navigate(Routes.Onboarding.route) {
                popUpTo(0)
            }
            isLoggedIn -> navController.navigate(Routes.Dashboard.route) {
                popUpTo(0)
            }
            else -> navController.navigate(Routes.Login.route) {
                popUpTo(0)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("MyApp", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
