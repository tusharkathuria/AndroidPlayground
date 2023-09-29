package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.AppComponent
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun activity(): AppCompatActivity = activity
    @Provides
    fun fragmentManager(): FragmentManager = activity.supportFragmentManager
    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)
    @ActivityScope
    @Provides
    fun screensNavigator(activity: AppCompatActivity): ScreensNavigator = ScreensNavigator(activity)
}