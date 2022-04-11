package com.tusharkathuria.androidplayground.github_explore.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tusharkathuria.androidplayground.github_explore.data.local.AppDatabase
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.RepoListResponseMapper
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.RepoResponseMapper
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoDetailViewModel
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoListViewModel

@Composable
fun Navigation(db: AppDatabase) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "repo_list") {
        composable("repo_list") {
            val repoListViewModel: RepoListViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return RepoListViewModel(
                            GithubRepo(GithubApiService.createGithubService(), RepoListResponseMapper(), RepoResponseMapper(), db)
                        ) as T
                    }
                }
            )

            RepoListScreen(
                repoListViewModel = repoListViewModel,
                onRepoClick = {
                    navController.navigate("repo_detail/$it")
                }
            )
        }

        composable("repo_detail/{owner}/{name}") {
            val ownerName = it.arguments?.getString("owner") ?: ""
            val name = it.arguments?.getString("name") ?: ""
            val repoDetailViewModel: RepoDetailViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return RepoDetailViewModel(
                            GithubRepo(GithubApiService.createGithubService(), RepoListResponseMapper(), RepoResponseMapper(), db),
                            ownerName = ownerName,
                            repoName = name
                        ) as T
                    }
                }
            )

            RepoDetailScreen(repoDetailViewModel = repoDetailViewModel)
        }
    }
}