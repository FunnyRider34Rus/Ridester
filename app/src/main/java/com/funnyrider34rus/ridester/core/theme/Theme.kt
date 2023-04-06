package com.funnyrider34rus.ridester.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = Ditto140,
    primaryContainer = Golbat60,
    secondary = Ditto140,
    background = Golbat0,
    surface = Golbat0,
    onPrimary = Golbat0,
    onSecondary = Golbat140,
    onBackground = Golbat140,
    onSurface = Golbat140,
    error = Krabby140
)

@Composable
fun RidesterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = typography,
        content = content
    )
}