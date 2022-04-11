package com.tusharkathuria.androidplayground.github_explore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tusharkathuria.androidplayground.github_explore.data.local.daos.GithubRepoDao
import com.tusharkathuria.androidplayground.github_explore.data.local.entities.GithubRepoEntity

@Database(entities = [GithubRepoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}