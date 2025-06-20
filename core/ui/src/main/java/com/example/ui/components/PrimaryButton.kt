package com.example.ui.components

import android.widget.Button
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.w3c.dom.Text

// PrimaryButton.kt
// Reusable button with loading indicator


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { if (!loading) onClick() },
        modifier = modifier.fillMaxWidth(),
        enabled = !loading
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(text = text)
        }
    }
}
