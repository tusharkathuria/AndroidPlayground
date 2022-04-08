package com.tusharkathuria.androidplayground.github_explore.data.remote.model

import com.google.gson.annotations.SerializedName

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
)

data class NetRepoListData(
    @SerializedName("total_count")
    val total: Int = 0,

    @SerializedName("items")
    val repos: List<NetRepoData> = emptyList(),

    @SerializedName("nextPage")
    val nextPage: Int? = null
)