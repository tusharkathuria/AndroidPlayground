package com.tusharkathuria.dagger_stackoverflow.screens.questionlist

import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchQuestionsUseCase(private val stackoverflowApi: StackoverflowApi) {
    sealed class Result {
        class Success(val questions: List<Question>): Result()
        object Failure : Result()
    }

    suspend fun fetch(): Result = withContext(Dispatchers.IO) {
        try {
            val response = stackoverflowApi.lastActiveQuestions(20)
            if (response.isSuccessful && response.body() != null) {
                return@withContext Result.Success(response.body()!!.questions)
            } else {
                return@withContext Result.Failure
            }
        } catch (t: Throwable) {
            if(t !is CancellationException) {
                return@withContext Result.Failure
            }
            throw t
        }
    }
}