package com.tusharkathuria.androidplayground.github_explore.app

import android.app.Application

class GithubExploreApp: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}