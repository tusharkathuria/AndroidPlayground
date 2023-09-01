package com.tusharkathuria.dagger_stackoverflow.common

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase

class ActivityCompositionRoot(private val activity: AppCompatActivity, private val appCompositionRoot: AppCompositionRoot) {
    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    private val fragmentManager get() = activity.supportFragmentManager
    private val layoutInflater get() = LayoutInflater.from(activity)
    val uiFactory get() = UIFactory(layoutInflater)
    val screensNavigator by lazy { ScreensNavigator(activity) }
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    val fetchQuestionDetailUseCase get()  = FetchQuestionDetailUseCase(stackoverflowApi)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
}