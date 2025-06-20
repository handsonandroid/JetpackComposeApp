package com.example.navigation// AppNavGraph.kt
// NavHost with routes for login, dashboard, user detail


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import feature.login.presentation.LoginScreen
import feature.userdetail.presentation.UserDetailScreen
import feature.dashboard.presentation.DashboardScreen

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Onboarding : Routes("onboarding")
    object Login : Routes("login")
    object Dashboard : Routes("dashboard")
    object UserDetail : Routes("user_detail")
}

@Composable
fun AppNavGraph(navController: NavHostController, isFirstLaunch: Boolean, isLoggedIn: Boolean) {
    val startDestination = when {
        isFirstLaunch -> Routes.Splash.route
        isLoggedIn -> Routes.Dashboard.route
        else -> Routes.Login.route
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.Splash.route) {
            feature.splash.SplashScreen(navController)
        }
        composable(Routes.Onboarding.route) {
            feature.onboarding.OnboardingScreen(onDone = {
                navController.navigate(if (isLoggedIn) Routes.Dashboard.route else Routes.Login.route) {
                    popUpTo(Routes.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(Routes.Login.route) { feature.login.presentation.LoginScreen(navController = navController) }
        composable(Routes.Dashboard.route) { feature.dashboard.presentation.DashboardScreen(navController = navController) }
        composable(Routes.UserDetail.route) {
            feature.userdetail.presentation.UserDetailScreen(
                user = feature.userdetail.presentation.User(
                    name = "Jane Doe",
                    email = "jane@example.com",
                    avatarUrl = "https://i.pravatar.cc/150"
                )
            )
        }
    }
}
