package com.tusharkathuria.androidplayground.github_explore.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoDetailViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "repo_list") {
        composable("repo_list") {
            RepoListScreen(
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