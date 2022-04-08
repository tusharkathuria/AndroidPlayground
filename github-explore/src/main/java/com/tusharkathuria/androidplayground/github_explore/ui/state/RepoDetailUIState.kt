package com.tusharkathuria.androidplayground.github_explore.ui.state

import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoData

data class RepoDetailUIState(
    val repo: NetRepoData? = null
)