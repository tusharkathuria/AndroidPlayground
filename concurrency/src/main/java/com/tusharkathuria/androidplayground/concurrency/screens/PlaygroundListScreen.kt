package com.tusharkathuria.androidplayground.concurrency.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tusharkathuria.androidplayground.concurrency.PlaygroundType

@Composable
fun PlaygroundListScreen(types: List<PlaygroundType>, onTypeClick: (PlaygroundType) -> Unit) {
    LazyColumn {
        items(types.size) {
            Text(text = types[it].name, modifier = Modifier.padding(16.dp).clickable {
                onTypeClick(types[it])
            })
            Divider()
        }
    }
}