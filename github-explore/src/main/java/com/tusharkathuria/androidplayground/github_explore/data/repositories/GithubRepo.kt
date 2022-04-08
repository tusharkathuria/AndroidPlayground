package com.tusharkathuria.androidplayground.github_explore.data.repositories

import com.tusharkathuria.androidplayground.github_explore.data.DataUIMapper
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoData
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoListData
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepoList

class GithubRepo(
    private val githubApiService: GithubApiService,
    private val repoListMapper: DataUIMapper<NetRepoListData, UIGithubRepoList>,
    private val repoMapper: DataUIMapper<NetRepoData, UIGithubRepo>
) {
    suspend fun getTopRepos(query: String): UIGithubRepoList {
        val data = githubApiService.getTopRepos(
            query = query,
            page = 1,
            itemsPerPage = 50
        )

        return repoListMapper.mapToUI(data)
    }

    suspend fun getRepo(ownerName: String, repoName: String): UIGithubRepo {
        val data = githubApiService.getRepo(ownerName, repoName)

        return repoMapper.mapToUI(data)
    }
}