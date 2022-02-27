package com.tusharkathuria.androidplayground.homenav.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tusharkathuria.androidplayground.homenav.models.Destination
import java.util.*
import com.tusharkathuria.androidplayground.homenav.R

@Composable
fun ColumnScope.HomeDrawerContent(
    onNavigationSelected: (destination: Destination) -> Unit,
    onLogout: () -> Unit
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Android Playground",
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Exploring compose navigation",
        fontSize = 16.sp
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    DrawerItem(label = stringResource(Destination.Upgrade.titleResource), modifier = Modifier.fillMaxWidth()) {
        onNavigationSelected(Destination.Upgrade)
    }
    Spacer(modifier = Modifier.height(8.dp))
    DrawerItem(modifier = Modifier.fillMaxWidth(), label = stringResource(Destination.Settings.titleResource)) {
        onNavigationSelected(Destination.Settings)
    }

    Spacer(modifier = Modifier.weight(1f))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.logout)
    ) {
        onLogout()
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
){
    Text(
        text = label.replaceFirstChar {it.titlecase(Locale.getDefault())},
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp)
    )
}