package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.app.Activity
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: Activity) {
    fun navigateBack() {
        activity.onBackPressed()
    }

    fun navigateToQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }
}