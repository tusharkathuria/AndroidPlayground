package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.questions.QuestionWithBody
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchQuestionDetailUseCase(retrofit: Retrofit) {
    sealed class Result {
        class Success(val question: QuestionWithBody): Result()
        object Failure : Result()
    }

    private val stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    suspend fun fetch(questionId: String): Result = withContext(Dispatchers.IO) {
        try {
            val response = stackoverflowApi.questionDetails(questionId)
            if (response.isSuccessful && response.body() != null) {
                return@withContext Result.Success(response.body()!!.question)
            } else {
                return@withContext Result.Failure
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                return@withContext Result.Failure
            }
            throw t
        }
    }
}