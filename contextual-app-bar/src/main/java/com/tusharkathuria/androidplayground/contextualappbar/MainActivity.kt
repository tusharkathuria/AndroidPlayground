package com.tusharkathuria.androidplayground.contextualappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tusharkathuria.androidplayground.commonui.theme.AndroidPlaygroundTheme
import com.tusharkathuria.androidplayground.commonui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()

            ProvideWindowInsets {
                AndroidPlaygroundTheme {
                    var isContextual by remember { mutableStateOf(false) }
                    val isLight = ProjectTheme.colors.isLight
                    val transition = updateTransition(isContextual, label = "isContextual")
                    val transitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Color> = {tween(300)}
                    val statusBarColor by transition.animateColor(label = "statusBarContextual", transitionSpec = transitionSpec) { isContextualMode ->
                        if (isContextualMode) ProjectTheme.colors.contextualStatusBar else ProjectTheme.colors.statusBar
                    }
                    val backgroundColor by transition.animateColor(label = "actionBarContextual", transitionSpec = transitionSpec) { isContextualMode ->
                        if (isContextualMode) ProjectTheme.colors.contextualAppBar else ProjectTheme.colors.appBar
                    }
                    val contentColor by transition.animateColor(label = "actionBarContentContextual", transitionSpec = transitionSpec) { isContextualMode ->
                        if (isContextualMode) ProjectTheme.colors.contextualAppBarContent else ProjectTheme.colors.appBarContent
                    }

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color.Transparent,
                            darkIcons = isLight.xor(isContextual)
                        )
                    }

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                backgroundColor = backgroundColor,
                                title = {
                                    Text(
                                        text = "Title",
                                        style = MaterialTheme.typography.h6,
                                        color = contentColor
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = "Back",
                                            tint = contentColor
                                        )
                                    }
                                },
                                actions = {
                                    if (isContextual) {
                                        IconButton(onClick = {}) {
                                            Icon(
                                                imageVector = Icons.Default.Share,
                                                contentDescription = "Share",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier.background(statusBarColor).statusBarsPadding().bottomElevation()
                            )
                        },
                        modifier = Modifier.navigationBarsPadding()
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            Button(
                                onClick = { isContextual = !isContextual },
                                modifier = Modifier.padding(top = 16.dp).align(Alignment.TopCenter)
                            ) {
                                Text(if (isContextual) "Switch to normal" else "Switch to contextual")
                            }
                        }
                    }
                }
            }
        }
    }
}

// Credits: https://stackoverflow.com/a/69914668
private fun Modifier.bottomElevation(): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = 8.dp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})