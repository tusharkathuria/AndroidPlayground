package com.tusharkathuria.androidplayground.homenav.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tusharkathuria.androidplayground.homenav.models.Destination

@Composable
fun ContentPlaceholder(modifier: Modifier = Modifier, destination: Destination) {
    val title = stringResource(id = destination.titleResource)
    Column(modifier.testTag(destination.path), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        destination.icon?.let {
            Icon(imageVector = it, contentDescription = title, modifier = Modifier.size(60.dp))
            Spacer(Modifier.size(16.dp))
        }
        Text(title, fontSize = 16.sp)
    }
}