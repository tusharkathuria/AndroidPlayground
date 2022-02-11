package com.tusharkathuria.androidplayground.contextualappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tusharkathuria.androidplayground.commonui.theme.AndroidPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            val normalBarColor = MaterialTheme.colors.surface
            val contextualBarColor = MaterialTheme.colors.onSurface
            var isContextual by remember { mutableStateOf(false)}
            val useDarkContent = MaterialTheme.colors.isLight.xor(isContextual)
            val barColor = if(isContextual) {contextualBarColor} else {normalBarColor}
            val contentColor = if(useDarkContent) { Color.Black } else { Color.White }

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = barColor,
                    darkIcons = useDarkContent
                )
            }
            AndroidPlaygroundTheme {
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