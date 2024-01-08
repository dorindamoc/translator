package com.example.translator_kmm.android.translate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.translator_kmm.core.presentation.Colors


// Simple modifiers can be made like this
//fun Modifier.gradientSurface(): Modifier = this.padding(8.dp)

// Stateful modifiers can be made like this
fun Modifier.gradientSurface(): Modifier = composed {
    if (isSystemInDarkTheme()) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(Colors.DarkGradientStart),
                    Color(Colors.DarkGradientEnd),
                )
            )
        )
    } else {
        Modifier.background(
            MaterialTheme.colorScheme.surface
        )

    }
}