package com.tusharkathuria.androidplayground.github_explore.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tusharkathuria.androidplayground.github_explore.data.local.entities.GithubRepoEntity
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepo

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM GithubRepo WHERE full_name LIKE :fullName LIMIT 1")
    suspend fun getRepo(fullName: String): GithubRepoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoDao: GithubRepoEntity)
}