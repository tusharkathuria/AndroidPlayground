package com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.model

import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>
}