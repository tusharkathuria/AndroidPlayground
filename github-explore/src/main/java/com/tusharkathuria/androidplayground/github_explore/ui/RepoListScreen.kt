package com.tusharkathuria.androidplayground.github_explore.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoListViewModel

@Composable
fun RepoListScreen(repoListViewModel: RepoListViewModel, onRepoClick: (String) -> Unit) {
    val uiState by repoListViewModel.uiState.collectAsState()
    val repos = uiState.uiGithubRepoList.list

    LaunchedEffect(key1 = true) {
        repoListViewModel.refreshTopRepos()
    }

    if(uiState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(repos.size) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRepoClick(repos[it].fullName) }
                    .padding(16.dp)) {
                    Text(repos[it].name)
                }
            }
        }
    }
}