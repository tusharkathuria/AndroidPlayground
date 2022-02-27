package com.tusharkathuria.androidplayground.homenav.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tusharkathuria.androidplayground.homenav.models.Destination
import com.tusharkathuria.androidplayground.homenav.models.NavigationBarItem
import com.tusharkathuria.androidplayground.homenav.R

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier) {
        NavigationBarItem.createAll(currentDestination, onNavigate).forEach {
            BottomNavigationItem(
                selected = it.selected,
                onClick = it.onClick,
                icon = it.icon,
                label = it.label
            )
        }
    }
}

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onAddClick: () -> Unit
) {
    NavigationRail(modifier, header = {
        FloatingActionButton( onClick = { onAddClick() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.create_item)
            )
        }
    }) {
        NavigationBarItem.createAll(currentDestination, onNavigate).forEach {
            NavigationRailItem(
                selected = it.selected,
                onClick = it.onClick,
                icon = it.icon,
                label = it.label
            )
        }
    }
}