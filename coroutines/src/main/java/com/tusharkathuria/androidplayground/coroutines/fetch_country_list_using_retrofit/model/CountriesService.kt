package com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com"

    fun getService(): CountriesApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CountriesApi::class.java)
    }
}