package com.tusharkathuria.dagger_stackoverflow.networking

import com.google.gson.annotations.SerializedName
import com.tusharkathuria.dagger_stackoverflow.questions.Question

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)