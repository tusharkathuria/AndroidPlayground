package com.tusharkathuria.androidplayground.homenav.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tusharkathuria.androidplayground.homenav.R
import com.tusharkathuria.androidplayground.homenav.models.Destination
import kotlinx.coroutines.launch

@Composable
fun Home(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    val navHostController = rememberNavController()
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()
    val currentDestination by derivedStateOf {
        navBackStackEntry.value?.destination?.route?.let {
            Destination.fromPath(it)
        } ?: run {
            Destination.Home
        }
    }
    val orientation = LocalConfiguration.current.orientation
    val isLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(modifier,
        scaffoldState = scaffoldState,
        topBar = {
            DestinationTopBar(
                destination = currentDestination,
                onNavigateUp = { navHostController.popBackStack() },
                onOpenDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                showSnackbar = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(it)
                    }
                }
            )
        },
        floatingActionButton = {
            if(currentDestination == Destination.Feed && !isLandscape)
                FloatingActionButton(onClick = { navHostController.navigate(Destination.Creation.path) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.create_item))
                }
        },
        bottomBar = {
            if(currentDestination.isRoot && !isLandscape) {
                BottomNavigationBar(currentDestination = currentDestination, onNavigate = {
                    navHostController.performRootNavigation(it)
                })
            }
        },
        drawerContent = {
            HomeDrawerContent(
                onNavigationSelected = {
                    navHostController.navigate(it.path)
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, onLogout = {

                }
            )
        }
    ) {
        Body(
            modifier = Modifier.fillMaxSize(),
            navHostController = navHostController,
            destination = currentDestination,
            orientation = orientation,
            onAddClick = { navHostController.navigate(Destination.Creation.path) },
            onNavigate = { navHostController.performRootNavigation(it) }
        )
    }
}

private fun NavHostController.performRootNavigation(destination: Destination) {
    navigate(destination.path) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    destination: Destination,
    orientation: Int,
    onAddClick: () -> Unit,
    onNavigate: (destination: Destination) -> Unit
){
    Row(modifier = modifier.fillMaxSize()) {
        if(destination.isRoot && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RailNavigationBar(
                currentDestination = destination,
                onAddClick = onAddClick,
                onNavigate = onNavigate
            )
        }
        Navigation(
            navHostController = navHostController
        )
    }
}

@Preview
@Composable
fun Check() {
    Text("")
}