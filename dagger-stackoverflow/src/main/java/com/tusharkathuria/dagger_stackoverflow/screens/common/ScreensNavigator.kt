package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityScope
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import javax.inject.Inject

interface ScreensNavigator {
    fun navigateBack()

    fun navigateToQuestionDetails(questionId: String)

    fun toViewModel()
}