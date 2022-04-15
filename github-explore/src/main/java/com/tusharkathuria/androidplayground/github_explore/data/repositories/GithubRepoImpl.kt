package com.tusharkathuria.androidplayground.github_explore.data.repositories

import com.tusharkathuria.androidplayground.github_explore.data.DataUIMapper
import com.tusharkathuria.androidplayground.github_explore.data.local.AppDatabase
import com.tusharkathuria.androidplayground.github_explore.data.local.entities.GithubRepoEntity
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoData
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoListData
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

interface GithubRepo {
    suspend fun getTopRepos(query: String): UIGithubRepoList
    suspend fun getRepo(ownerName: String, repoName: String): UIGithubRepo
}

class GithubRepoImpl(
    private val githubApiService: GithubApiService,
    private val repoListMapper: DataUIMapper<NetRepoListData, UIGithubRepoList>,
    private val repoMapper: DataUIMapper<NetRepoData, UIGithubRepo>,
    private val db: AppDatabase
): GithubRepo {

    override suspend fun getTopRepos(query: String): UIGithubRepoList {
        val data = githubApiService.getTopRepos(
            query = query,
            page = 1,
            itemsPerPage = 50
        )

        return repoListMapper.mapToUI(data)
    }

    override suspend fun getRepo(ownerName: String, repoName: String): UIGithubRepo {
        val entity = db.githubRepoDao().getRepo("$ownerName/$repoName")

        if(entity != null) {
            return UIGithubRepo(
                entity.id,
                entity.name,
                entity.fullName,
                entity.description,
                entity.url
            )
        }

        val data = githubApiService.getRepo(ownerName, repoName)

        db.githubRepoDao().insertRepo(GithubRepoEntity(
            data.id,
            data.name,
            data.fullName,
            data.description,
            data.url
        ))

        return repoMapper.mapToUI(data)
    }
}