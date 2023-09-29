package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.AppComponent
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): AppCompatActivity
    fun stackoverflowApi(): StackoverflowApi
    fun fragmentManager(): FragmentManager
    fun layoutInflater(): LayoutInflater
    fun screensNavigator(): ScreensNavigator
}