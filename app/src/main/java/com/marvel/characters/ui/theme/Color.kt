package com.marvel.characters.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val gradientBrush = Brush.verticalGradient(
    colors = listOf(Color(0xEFC3C2C2), Color(0xFF131313)) // Dark to Light
)
val gradientBrush1 = Brush.verticalGradient(
    colors = listOf(
        Color(0xE9050512), Color(0xE71A1A07), Color(0xF4000000)
    ) // Dark to Light
)
val SecondaryFontColor = gradientBrush1
val DefaultBackgroundColor = Color(0xFFFAFAFA)