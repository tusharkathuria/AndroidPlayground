package com.tusharkathuria.androidplayground.contextualappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tusharkathuria.androidplayground.commonui.theme.AndroidPlaygroundTheme
import com.tusharkathuria.androidplayground.commonui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidPlaygroundTheme {
                val systemUiController = rememberSystemUiController()
                var isContextual by remember { mutableStateOf(false)}
                val transition = updateTransition(isContextual, label = "isContextual")
                val isLight = ProjectTheme.colors.isLight

                val statusBarColor by transition.animateColor(label = "statusBarContextual") { isContextualMode ->
                    if (isContextualMode) ProjectTheme.colors.contextualStatusBar else ProjectTheme.colors.statusBar
                }

                val barColor by transition.animateColor(label = "actionBarContextual") { isContextualMode ->
                    if (isContextualMode) ProjectTheme.colors.contextualAppBar else ProjectTheme.colors.appBar
                }

                val contentColor by transition.animateColor(label = "actionBarContentContextual") { isContextualMode ->
                    if (isContextualMode) ProjectTheme.colors.contextualAppBarContent else ProjectTheme.colors.appBarContent
                }

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor,
                        darkIcons = isLight.xor(isContextual)
                    )
                }

                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                backgroundColor = barColor,
                                title = {
                                    Text(
                                        text = "Title",
                                        style = MaterialTheme.typography.h6,
                                        color = contentColor
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {isContextual = false}) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = "Back",
                                            tint = contentColor
                                        )
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Box(Modifier.fillMaxSize()) {
                        Button(onClick = { isContextual = !isContextual}, modifier = Modifier.align(Alignment.Center)) {
                            Text(if(isContextual) "Switch to normal" else "Switch to contextual")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidPlaygroundTheme {
        Greeting("Android")
    }
}