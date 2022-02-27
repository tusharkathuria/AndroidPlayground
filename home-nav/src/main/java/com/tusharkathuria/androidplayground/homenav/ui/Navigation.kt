package com.tusharkathuria.androidplayground.homenav.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tusharkathuria.androidplayground.homenav.models.Destination

@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(navController = navHostController, modifier = modifier, startDestination = Destination.Home.path) {
        navigation(
            startDestination = Destination.Feed.path,
            route = Destination.Home.path
        ) {
            composable(Destination.Feed.path) {
                ContentPlaceholder(destination = Destination.Feed, modifier = Modifier.fillMaxSize())
            }
            composable(Destination.Calendar.path) {
                ContentPlaceholder(destination = Destination.Calendar, modifier = Modifier.fillMaxSize())
            }
            composable(Destination.Contacts.path) {
                ContentPlaceholder(destination = Destination.Contacts, modifier = Modifier.fillMaxSize())
            }
        }
        composable(Destination.Settings.path) {
            ContentPlaceholder(destination = Destination.Settings, modifier = Modifier.fillMaxSize())
        }
        composable(Destination.Upgrade.path) {
            ContentPlaceholder(destination = Destination.Upgrade, modifier = Modifier.fillMaxSize())
        }
        navigation(
            startDestination = Destination.Add.path,
            route = Destination.Creation.path
        ) {
            composable(route = Destination.Add.path) {
                ContentPlaceholder(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Add
                )
            }
        }
    }
}