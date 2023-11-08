package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityScope
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigator @Inject constructor(private val activity: AppCompatActivity) {
    fun navigateBack() {
        activity.onBackPressed()
    }

    fun navigateToQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }
}