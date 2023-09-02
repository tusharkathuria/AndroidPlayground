package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import com.tusharkathuria.dagger_stackoverflow.Constants
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }
}