package com.tusharkathuria.androidplayground.homenav.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.tusharkathuria.androidplayground.homenav.R

sealed class Destination(val path: String, val titleResource: Int, val icon: ImageVector? = null, val isRoot: Boolean = true) {
    object Home : Destination("home", R.string.home) // Feature root (Not actual destination)
    object Feed : Destination("feed", R.string.feed, Icons.Default.List)
    object Contacts : Destination("contacts", R.string.contacts, Icons.Default.Person)
    object Calendar : Destination("calendar", R.string.calendar, Icons.Default.DateRange)

    object Settings : Destination("settings", R.string.settings, Icons.Default.Settings, false)
    object Upgrade : Destination("upgrade", R.string.upgrade, Icons.Default.Star, false)

    object Creation : Destination("creation", R.string.creation, isRoot = false) // Feature root (Not actual destination)
    object Add : Destination("add", R.string.add, Icons.Default.Add, isRoot = false)


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