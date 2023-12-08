package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app

import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.Constants
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Retrofit1
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
    @Retrofit1  // We can  also use @Named("retrofit1") instead
    fun retrofit1() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    @AppScope
//    @Provides
//    @Retrofit2
//    fun retrofit2() = Retrofit.Builder()
//        .baseUrl("other_url")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    @Provides
    fun application(): Application = application

    @AppScope
    @Provides
    fun stackOverflowApi(@Retrofit1 retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)
}