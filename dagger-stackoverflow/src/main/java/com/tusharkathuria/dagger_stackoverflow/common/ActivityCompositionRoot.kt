package com.tusharkathuria.dagger_stackoverflow.common

import android.app.Activity
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase

class ActivityCompositionRoot(private val activity: Activity, private val appCompositionRoot: AppCompositionRoot) {
    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val screensNavigator by lazy { ScreensNavigator(activity) }
    val fetchQuestionDetailUseCase get()  = FetchQuestionDetailUseCase(stackoverflowApi)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
}