package com.example.navigation// AppNavGraph.kt
// NavHost with routes for login, dashboard, user detail


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashboard.DashboardScreen
import com.example.login.LoginScreen
import com.example.onboarding.OnboardingScreen
import com.example.splash.SplashScreen
import com.example.userdetail.UserDetailScreen

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
            SplashScreen(navController)
        }
        composable(Routes.Onboarding.route) {
            OnboardingScreen(onDone = {
                navController.navigate(if (isLoggedIn) Routes.Dashboard.route else Routes.Login.route) {
                    popUpTo(Routes.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(Routes.Login.route) { LoginScreen(navController = navController) }
        composable(Routes.Dashboard.route) { DashboardScreen(navController = navController) }
        composable(Routes.UserDetail.route) {
            UserDetailScreen(
                user = com.example.userdetail.User(
                    name = "Jane Doe",
                    email = "jane@example.com",
                    avatarUrl = "https://i.pravatar.cc/150"
                )
            )
        }
    }
}
