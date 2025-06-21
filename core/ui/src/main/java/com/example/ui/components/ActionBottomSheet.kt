package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ActionBottomSheet(
    actions: List<String>,
    onActionClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        actions.forEachIndexed { index, action ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onActionClicked(action) }
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = action,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )
            }
            if (index < actions.lastIndex) {
                Divider(color = Color.LightGray)
            }
        }
    }
}
