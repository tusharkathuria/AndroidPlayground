package com.tusharkathuria.androidplayground.homenav.models

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class NavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {
    companion object {
        fun createAll(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Feed,
                Destination.Contacts,
                Destination.Calendar
            ).map {
                NavigationBarItem(
                    icon = {
                        it.icon?.let { image ->
                            Icon(
                                imageVector = image,
                                contentDescription = null
                            ) }
                    },
                    label = {
                        Text(text = stringResource(id = it.titleResource))
                    },
                    selected = currentDestination == it,
                    onClick = {onNavigate(it)}
                )
            }
        }
    }
}