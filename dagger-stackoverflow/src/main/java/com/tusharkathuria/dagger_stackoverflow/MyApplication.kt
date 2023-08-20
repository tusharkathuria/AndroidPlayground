package com.tusharkathuria.dagger_stackoverflow


import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    // It is important to initialise these using getters so that different features get their own instances
    val fetchQuestionDetailUseCase get()  = FetchQuestionDetailUseCase(stackoverflowApi)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)

    override fun onCreate() {
        super.onCreate()
    }

}