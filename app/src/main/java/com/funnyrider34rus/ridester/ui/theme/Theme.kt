package com.funnyrider34rus.ridester.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Ditto140,
    primaryVariant = Golbat60,
    secondary = Ditto140,
    background = Golbat0,
    surface = Golbat0,
    /*onPrimary = Golbat0,
    onSecondary = Golbat140,
    onBackground = Golbat140,
    onSurface = Golbat140*/
)

@Composable
fun RidesterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}