package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.common.AppPreferences
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                var isFirstLaunch by remember { mutableStateOf(AppPreferences.isFirstLaunch(this)) }
                var isLoggedIn by remember { mutableStateOf(AppPreferences.isLoggedIn(this)) }

                // Listen for navigation changes and refresh state after onboarding, login, or logout
                LaunchedEffect(navController) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        when (destination.route) {
                            "onboarding" -> isFirstLaunch = AppPreferences.isFirstLaunch(this@MainActivity)
                            "login", "dashboard" -> isLoggedIn = AppPreferences.isLoggedIn(this@MainActivity)
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavGraph(
                        navController = navController,
                        isFirstLaunch = isFirstLaunch,
                        isLoggedIn = isLoggedIn
                    )
                }
            }
        }
    }
}