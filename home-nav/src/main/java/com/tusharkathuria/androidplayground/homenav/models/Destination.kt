package com.tusharkathuria.androidplayground.homenav.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val path: String, val title: String, val icon: ImageVector? = null, val isRoot: Boolean = true) {
    object Home : Destination("home", "Home") // Feature root (Not actual destination)
    object Feed : Destination("feed", "Feed", Icons.Default.List)
    object Contacts : Destination("contacts", "Contacts", Icons.Default.Person)
    object Calendar : Destination("calendar", "Calendar", Icons.Default.DateRange)

    object Settings : Destination("settings", "Settings", Icons.Default.Settings, false)
    object Upgrade : Destination("upgrade", "Upgrade", Icons.Default.Star, false)

    object Creation : Destination("creation", "Creation", isRoot = false) // Feature root (Not actual destination)
    object Add : Destination("add", "Add", Icons.Default.Add, isRoot = false)

    companion object {
        fun fromPath(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Settings.path -> Settings
                Upgrade.path -> Upgrade
                Creation.path -> Creation
                Add.path -> Add
                else -> Home
            }
        }
    }
}