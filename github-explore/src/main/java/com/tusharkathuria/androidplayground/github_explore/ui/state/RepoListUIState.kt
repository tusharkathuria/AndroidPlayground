package com.tusharkathuria.androidplayground.github_explore.ui.state

import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoListData
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepoList

data class RepoListUIState(
    val uiGithubRepoList: UIGithubRepoList = UIGithubRepoList(emptyList())
)