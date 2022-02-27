package com.tusharkathuria.androidplayground.homenav.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.homenav.models.Destination

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    destination: Destination,
    onNavigateUp: () -> Unit,
    onOpenDrawer: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    if (destination.isRoot) {
        RootDestinationTopBar(
            modifier,
            destination = destination,
            openDrawer = onOpenDrawer,
            showSnackbar = showSnackbar
        )
    } else {
        ChildDestinationTopBar(
            modifier,
            destination.title,
            onNavigateUp
        )
    }
}

@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    destination: Destination,
    openDrawer: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    TopAppBar(
        title = { Text(destination.title) },
        actions = {
            if(destination != Destination.Feed) {
                IconButton(onClick = {
                    showSnackbar("Not available yet")
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "More info")
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) { Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Open menu"
            ) }
        },
        modifier = modifier
    )
}

@Composable
fun ChildDestinationTopBar(modifier: Modifier = Modifier, title: String, onNavigateUp: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}