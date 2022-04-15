package com.tusharkathuria.androidplayground.github_explore.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepo
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepoImpl
import com.tusharkathuria.androidplayground.github_explore.ui.state.RepoListUIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class RepoListViewModel(val githubRepo: GithubRepo): ViewModel() {

    val uiState = MutableStateFlow(RepoListUIState())

    fun refreshTopRepos() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoading = true)
            githubRepo.getTopRepos("android").also {
                withContext(Dispatchers.Main) {
                    uiState.value = uiState.value.copy(uiGithubRepoList = it, isLoading = false)
                }
            }
        }
    }
}