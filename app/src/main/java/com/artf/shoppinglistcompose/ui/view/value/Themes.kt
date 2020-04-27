package com.artf.shoppinglistcompose.ui.view.value

import androidx.ui.graphics.Color
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

val lightThemeColors = lightColorPalette(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF03DAC6),
    secondaryVariant = Color(0xFF018786),
    onSecondary = Color(0xFF000000),
    background = Color(0xFFEEEEEE),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

val darkThemeColors = darkColorPalette(
    primary = Color(0xFFEA6D7E),
    primaryVariant = Color(0xFFDD0D3E),
    onPrimary = Color.Black,
    secondary = Color(0xFF121212),
    onSecondary = Color.White,
    surface = Color(0xFF121212),
    background = Color(0xFF121212),
    onBackground = Color.White,
    onSurface = Color.White
)
