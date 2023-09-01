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
    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val fragmentManager get() = activity.supportFragmentManager
    val layoutInflater get() = LayoutInflater.from(activity)
    val screensNavigator by lazy { ScreensNavigator(activity) }
}