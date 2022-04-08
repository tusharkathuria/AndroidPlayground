package com.tusharkathuria.androidplayground.github_explore.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.ui.state.RepoListUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoListViewModel(githubApiService: GithubApiService = GithubApiService.createGithubService()): ViewModel() {

    val uiState = MutableStateFlow(RepoListUIState())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                githubApiService.getTopRepos("android", 1, 50).also {
                    withContext(Dispatchers.Main) {
                        uiState.value = uiState.value.copy(list = it)
                    }
                }
            }
        }
    }
}