package com.example.ui.theme// Shape.kt
// Rounded corners used in app


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(Dimens.CornerRadius),
    large = RoundedCornerShape(0.dp)
)
