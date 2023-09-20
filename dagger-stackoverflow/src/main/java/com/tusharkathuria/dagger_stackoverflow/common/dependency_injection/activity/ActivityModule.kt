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
class ActivityModule(private val activity: AppCompatActivity, private val appComponent: AppComponent) {

    private val screensNavigator by lazy {
        ScreensNavigator(activity)
    }
    @Provides
    fun activity(): AppCompatActivity = activity
    @Provides
    fun stackoverflowApi(): StackoverflowApi = appComponent.stackOverflowApi()
    @Provides
    fun fragmentManager(): FragmentManager = activity.supportFragmentManager
    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)
    @Provides
    fun screensNavigator(activity: AppCompatActivity): ScreensNavigator = screensNavigator
}