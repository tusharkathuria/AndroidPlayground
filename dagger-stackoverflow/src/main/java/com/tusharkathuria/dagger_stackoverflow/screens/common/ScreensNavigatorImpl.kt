package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityScope
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import com.tusharkathuria.dagger_stackoverflow.screens.viewmodel.ViewModelActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigatorImpl @Inject constructor(private val activity: AppCompatActivity): ScreensNavigator {
    override fun navigateBack() {
        activity.onBackPressed()
    }

    override fun navigateToQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }

    override fun toViewModel() {
        ViewModelActivity.start(activity)
    }
}