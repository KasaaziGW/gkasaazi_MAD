package com.example.to_doapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val CustomLightColorPalette = lightColors(
    primary = BlueLightPrimary,
    primaryVariant = BlueLightVariant,
    onPrimary = Color.White,
    secondary = BlueLightDark
)

private val CustomDarkColorPalette = darkColors(
    primary = BlueDarkPrimary,
    primaryVariant = BlueDarkVariant,
    onPrimary = Color.White,
    secondary = BlueDark
)

@Composable
fun ToDoAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        CustomDarkColorPalette
    } else {
        CustomLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = CustomTypography,
        shapes = Shapes,
        content = content
    )
}