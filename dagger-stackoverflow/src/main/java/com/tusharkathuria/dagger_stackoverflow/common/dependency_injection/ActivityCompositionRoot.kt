package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator

class ActivityCompositionRoot(private val activity: AppCompatActivity, private val appCompositionRoot: AppCompositionRoot) {
    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val fragmentManager get() = activity.supportFragmentManager
    val layoutInflater get() = LayoutInflater.from(activity)
    val screensNavigator by lazy { ScreensNavigator(activity) }
}