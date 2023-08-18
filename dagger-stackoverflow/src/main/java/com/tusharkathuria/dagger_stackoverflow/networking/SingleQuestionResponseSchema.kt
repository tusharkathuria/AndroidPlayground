package com.tusharkathuria.dagger_stackoverflow.networking

import com.google.gson.annotations.SerializedName
import com.tusharkathuria.dagger_stackoverflow.questions.QuestionWithBody

data class SingleQuestionResponseSchema(@SerializedName("items") val questions: List<QuestionWithBody>) {
    val question: QuestionWithBody get() = questions[0]
}