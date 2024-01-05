package com.example.translator_kmm.android.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.example.translator_kmm.core.presentation.Colors

val AccentViolet = Color(Colors.AccentViolet)
val DarkGrey = Color(Colors.DarkGrey)
val LightBlue = Color(Colors.LightBlue)
val LightBlueGrey = Color(Colors.LightBlueGrey)
val TextBlack = Color(Colors.TextBlack)

val lightColors = lightColorScheme(
    primary = AccentViolet,
    onPrimary = Color.White,
    secondary = AccentViolet,
    background = LightBlueGrey,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack,
    error = Color.Red,
    onError = Color.White)

val darkColors = darkColorScheme(
    primary = AccentViolet,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White,
    error = Color.Red,
    onError = Color.White)