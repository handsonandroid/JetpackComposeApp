package com.example.userdetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.components.ActionBottomSheet
import com.example.ui.components.PrimaryButton
import com.example.ui.theme.MyComposeAppTheme

// Sample data class representing user (can be in shared module)
data class User(
    val name: String,
    val email: String,
    val avatarUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: User) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            ActionBottomSheet(
                actions = listOf("Edit", "Share", "Logout"),
                onActionClicked = {
                    showSheet = false
                    // Handle action click
                }
            )
        }
    }

    MyComposeAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )

            Spacer(Modifier.height(16.dp))
            Text(user.name, style = MaterialTheme.typography.headlineSmall)
            Text(user.email, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(32.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard("Followers", "1.2K")
                StatCard("Posts", "34")
                StatCard("Following", "100")
            }

            Spacer(Modifier.height(32.dp))
            PrimaryButton("More Actions", loading = false) {
                showSheet = true
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge)
        Text(label, style = MaterialTheme.typography.labelMedium)
    }
}
