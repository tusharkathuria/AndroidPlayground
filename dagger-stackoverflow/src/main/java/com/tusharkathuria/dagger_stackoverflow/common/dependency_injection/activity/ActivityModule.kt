package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.AppComponent
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @ActivityScope
    @Binds
    abstract fun screensNavigator(screensNavigatorImpl: ScreensNavigatorImpl): ScreensNavigator

    companion object {
        @Provides
        fun fragmentManager(activity: AppCompatActivity): FragmentManager =
            activity.supportFragmentManager

        @Provides
        fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)
    }
}