package com.tusharkathuria.dagger_stackoverflow.common

import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.DaggerActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {
    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent((application as MyApplication).appComponent)
            .activityModule(ActivityModule(this)).build()
    }
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent(activityComponent)
            .presentationModule(PresentationModule()).build()
    }
    protected val injector get() = presentationComponent
}