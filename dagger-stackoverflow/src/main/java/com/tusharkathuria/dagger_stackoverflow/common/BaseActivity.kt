package com.tusharkathuria.dagger_stackoverflow.common

import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityModule

open class BaseActivity: AppCompatActivity() {
    val activityComponent: ActivityComponent by lazy {
        (application as MyApplication).appComponent.activityComponentBuilder()
            .activityModule(ActivityModule)
            .activity(this)
            .build()
    }
    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }
    protected val injector get() = presentationComponent
}