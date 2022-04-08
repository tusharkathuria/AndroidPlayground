package com.tusharkathuria.androidplayground.github_explore.data.remote.model

import com.google.gson.annotations.SerializedName
import com.tusharkathuria.androidplayground.github_explore.data.DataModel
import com.tusharkathuria.androidplayground.github_explore.data.DataUIMapper
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepoList

data class NetRepoData (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String
): DataModel()

data class NetRepoListData(
    @SerializedName("total_count")
    val total: Int = 0,

    @SerializedName("items")
    val repos: List<NetRepoData> = emptyList(),

    @SerializedName("nextPage")
    val nextPage: Int? = null
): DataModel()

class RepoListResponseMapper: DataUIMapper<NetRepoListData, UIGithubRepoList> {
    override fun mapToUI(dataModel: NetRepoListData): UIGithubRepoList {
        return UIGithubRepoList(list = dataModel.repos.map { UIGithubRepo(
            it.id, it.name, it.fullName, it.description, it.url
        )})
    }

    override fun mapToData(uiModel: UIGithubRepoList): NetRepoListData {
        TODO("Not yet implemented")
    }
}

class RepoResponseMapper: DataUIMapper<NetRepoData, UIGithubRepo> {
    override fun mapToUI(dataModel: NetRepoData): UIGithubRepo {
        return UIGithubRepo(dataModel.id, dataModel.name, dataModel.fullName, dataModel.description, dataModel.url)
    }

    override fun mapToData(uiModel: UIGithubRepo): NetRepoData {
        TODO("Not yet implemented")
    }
}