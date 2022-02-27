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
import androidx.compose.ui.res.stringResource
import com.tusharkathuria.androidplayground.homenav.R
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
            stringResource(id = destination.titleResource),
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
        title = { Text(stringResource(id = destination.titleResource)) },
        actions = {
            val message = stringResource(id = R.string.not_available_yet)
            if(destination != Destination.Feed) {
                IconButton(onClick = {
                    showSnackbar(message)
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = stringResource(id = R.string.more_info))
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) { Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.open_menu)
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
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        }
    )
}