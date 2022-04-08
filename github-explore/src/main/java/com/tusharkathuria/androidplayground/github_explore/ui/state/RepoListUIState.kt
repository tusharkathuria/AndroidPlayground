package com.tusharkathuria.androidplayground.github_explore.ui.state

import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoListData

data class RepoListUIState(
    val list: NetRepoListData = NetRepoListData()
)