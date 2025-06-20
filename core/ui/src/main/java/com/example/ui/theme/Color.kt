package com.example.ui.theme// Color.kt
// Light/dark theme color setup


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFF0D47A1)
val SecondaryColor = Color(0xFF1976D2)
val AccentColor = Color(0xFF64B5F6)

val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    surface = Color(0xFFF5F5F5)
)

val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color.White,
    surface = Color(0xFF1E1E1E)
)
