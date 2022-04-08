package com.tusharkathuria.androidplayground.github_explore.ui.models

data class UIGithubRepo(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val url: String
): UIModel()