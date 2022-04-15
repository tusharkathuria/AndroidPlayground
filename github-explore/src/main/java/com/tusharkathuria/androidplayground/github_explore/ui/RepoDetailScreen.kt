package com.tusharkathuria.androidplayground.github_explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoDetailViewModel

@Composable
fun RepoDetailScreen(repoDetailViewModel: RepoDetailViewModel) {
    val uiState by repoDetailViewModel.uiState.collectAsState()
    val repo = uiState.repo

    repo?.let {
        if(uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(it.name)
                Text(it.description)
                Text(it.url)
            }
        }
    }
}