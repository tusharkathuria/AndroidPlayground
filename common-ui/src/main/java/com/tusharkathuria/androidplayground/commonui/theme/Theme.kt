package com.tusharkathuria.androidplayground.commonui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = AndroidPlaygroundColors(
    appBar = GreyMedium,
    contextualAppBar = Color.White,
    statusBar = GreyDark,
    contextualStatusBar = Color.White,
    appBarContent = Color.White,
    contextualAppBarContent = GreyMedium,
    material = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
    ),
    isLight = false
)

private val LightColorPalette = AndroidPlaygroundColors(
    appBar = Color.White,
    contextualAppBar = GreyMedium,
    statusBar = Color.White,
    contextualStatusBar = GreyDark,
    appBarContent = GreyMedium,
    contextualAppBarContent = Color.White,
    material = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200
    ),
    isLight = true
)

object ProjectTheme {
    val colors: AndroidPlaygroundColors
        @Composable
        get() = LocalAndroidPlaygroundColors.current
}

/**
 * AndroidPlayground custom Color Palette
 */
@Stable
class AndroidPlaygroundColors(
    material: Colors,
    appBar: Color,
    contextualAppBar: Color,
    statusBar: Color,
    contextualStatusBar: Color,
    appBarContent: Color,
    contextualAppBarContent: Color,
    isLight: Boolean
) {
    var appBar by mutableStateOf(appBar)
        private set

    var materialColors by mutableStateOf(material)
        private set

    var isLight by mutableStateOf(isLight)
            private set

    var contextualAppBar by mutableStateOf(contextualAppBar)
        private set

    var statusBar by mutableStateOf(statusBar)
        private set

    var contextualStatusBar by mutableStateOf(contextualStatusBar)
        private set

    var appBarContent by mutableStateOf(appBarContent)
        private set

    var contextualAppBarContent by mutableStateOf(contextualAppBarContent)
        private set

    fun update(other: AndroidPlaygroundColors) {
        appBar = other.appBar
        materialColors = other.materialColors
        isLight = other.isLight
        contextualAppBar = other.contextualAppBar
        statusBar = other.statusBar
        contextualStatusBar = other.contextualStatusBar
        appBarContent = other.appBarContent
        contextualAppBarContent = other.contextualAppBarContent
    }

    fun copy(): AndroidPlaygroundColors = AndroidPlaygroundColors(
        appBar = appBar,
        material = materialColors,
        isLight = isLight,
        contextualAppBar = contextualAppBar,
        statusBar = statusBar,
        contextualStatusBar = contextualStatusBar,
        appBarContent = appBarContent,
        contextualAppBarContent = contextualAppBarContent
    )
}

@Composable
fun ProvideAndroidPlaygroundColors(
    colors: AndroidPlaygroundColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalAndroidPlaygroundColors provides colorPalette, content = content)
}

private val LocalAndroidPlaygroundColors = staticCompositionLocalOf<AndroidPlaygroundColors> {
    error("No AndroidPlaygroundColorPalette provided")
}

@Composable
fun AndroidPlaygroundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    ProvideAndroidPlaygroundColors(colors) {
        MaterialTheme(
            colors = colors.materialColors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}