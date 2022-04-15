package com.tusharkathuria.androidplayground.github_explore.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepo
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepoImpl
import com.tusharkathuria.androidplayground.github_explore.ui.state.RepoDetailUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoDetailViewModel(githubRepo: GithubRepo, ownerName: String, repoName: String): ViewModel() {
    val uiState = MutableStateFlow(RepoDetailUIState())

    init {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoading = true)
            withContext(Dispatchers.IO) {
                githubRepo.getRepo(ownerName, repoName).also {
                    withContext(Dispatchers.Main) {
                        uiState.value = uiState.value.copy(repo = it, isLoading = false)
                    }
                }
            }
        }
    }
}