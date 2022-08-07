package com.tusharkathuria.androidplayground.concurrency.app

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.tusharkathuria.androidplayground.concurrency.PlaygroundType
import com.tusharkathuria.androidplayground.concurrency.screens.PlaygroundListScreen
import com.tusharkathuria.androidplayground.concurrency.screens.PlaygroundScreen

enum class AppScreens {
    PlaygroundList,
    Playground
}

/**
 * Simple list detail navigation. This can be replaced with Compose Nav library once it becomes more complex
 */
@Composable
fun AppUI() {
    var currentScreen by remember { mutableStateOf(AppScreens.PlaygroundList) }
    var currentPlayground : PlaygroundType? by remember { mutableStateOf(null) }

    when(currentScreen) {
        AppScreens.PlaygroundList -> PlaygroundListScreen(
            types = PlaygroundType.values().toList(),
            onTypeClick = {
                currentScreen = AppScreens.Playground
                currentPlayground = it
            }
        )
        AppScreens.Playground -> currentPlayground?.let {
            PlaygroundScreen(type = it)
        }
    }

    BackHandler(enabled = currentScreen != AppScreens.PlaygroundList) {
        if(currentScreen == AppScreens.Playground) {
            currentScreen = AppScreens.PlaygroundList
        }
    }
}