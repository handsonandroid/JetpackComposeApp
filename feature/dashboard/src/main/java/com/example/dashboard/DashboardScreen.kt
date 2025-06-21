package com.example.dashboard// DashboardScreen.kt
// Shows cards for users/revenue and list of recent activities


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.AppPreferences
import com.example.ui.components.DashboardCard
import com.example.ui.theme.MyComposeAppTheme
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    val context = LocalContext.current
    MyComposeAppTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardCard("Users", "1.2K", Icons.Default.Person)
                DashboardCard("Revenue", "$12.4K", Icons.Default.AttachMoney)
            }

            Spacer(Modifier.height(24.dp))
            Text("Recent Activity", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(8.dp))
            repeat(5) {
                ListItem(
                    headlineText = { Text("Activity $it") },
                    supportingText = { Text("Completed task $it") },
                    trailingContent = {
                        if (it == 0) {
                            Button(onClick = {
                                navController.navigate("user_detail")
                            }) {
                                Text("Profile")
                            }
                        }
                    }
                )
            }
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                AppPreferences.setLoggedIn(context, false)
                navController.navigate("login") {
                    popUpTo("dashboard") { inclusive = true }
                }
            }) {
                Text("Logout")
            }
        }
    }
}
