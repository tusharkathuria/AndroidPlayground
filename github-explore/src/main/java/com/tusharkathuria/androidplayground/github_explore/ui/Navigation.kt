package com.tusharkathuria.androidplayground.github_explore.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tusharkathuria.androidplayground.github_explore.app.GithubExploreApp
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoDetailViewModel
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as GithubExploreApp
    val githubRepository = application.appContainer.githubRepo

    NavHost(navController = navController, startDestination = "repo_list") {
        composable("repo_list") {
            val repoListViewModel: RepoListViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return RepoListViewModel(githubRepository) as T
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
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return RepoDetailViewModel(
                            githubRepository,
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