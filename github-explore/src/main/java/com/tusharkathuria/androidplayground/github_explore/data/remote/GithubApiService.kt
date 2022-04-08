package com.tusharkathuria.androidplayground.github_explore.data.remote

import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoData
import com.tusharkathuria.androidplayground.github_explore.data.remote.model.NetRepoListData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    companion object {
        fun createGithubService(): GithubApiService {
            val retrofit = Retrofit.Builder()
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
                .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }

    @GET("search/repositories?sort=stars")
    suspend fun getTopRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): NetRepoListData

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): NetRepoData
}