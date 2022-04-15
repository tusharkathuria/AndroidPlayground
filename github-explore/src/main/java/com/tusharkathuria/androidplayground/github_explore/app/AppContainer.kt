package com.tusharkathuria.androidplayground.github_explore.app

import android.app.Application
import androidx.room.Room
import com.tusharkathuria.androidplayground.github_explore.data.local.AppDatabase
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.RepoListResponseMapper
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.RepoResponseMapper
import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(application: Application) {
    private val githubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Accept-Encoding", "identity")
                    chain.proceed(request.build())
                }
                .build()
        )
        .build().create(GithubApiService::class.java)

    private val repoListResponseMapper = RepoListResponseMapper()
    private val repoResponseMapper = RepoResponseMapper()
    private val db = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "githubexplore"
    ).build()

    val githubRepo = GithubRepoImpl(githubApiService, repoListResponseMapper, repoResponseMapper, db)
}