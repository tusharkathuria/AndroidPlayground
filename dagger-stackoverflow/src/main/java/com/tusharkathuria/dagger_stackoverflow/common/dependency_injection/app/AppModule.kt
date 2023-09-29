package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app

import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.Constants
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @AppScope
    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun application(): Application = application

    @AppScope
    @Provides
    fun stackOverflowApi(retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)
}