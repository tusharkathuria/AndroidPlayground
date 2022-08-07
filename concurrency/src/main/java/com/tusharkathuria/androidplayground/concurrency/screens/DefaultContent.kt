package com.tusharkathuria.androidplayground.concurrency.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor
import androidx.compose.runtime.*

@Composable
fun DefaultContent(executor: IDefaultExecutor, modifier: Modifier = Modifier) {
    var isStarted by remember { mutableStateOf(false) }
    val text = if(isStarted) { "Click start to initiate execution" } else { "Concurrent execution is happening in background" }
    Column(modifier) {
        Text(text)
        Button(enabled = !isStarted, onClick = {
            executor.execute()
            isStarted = true
        }) {
            Text(text = "Start work")
        }
    }
}