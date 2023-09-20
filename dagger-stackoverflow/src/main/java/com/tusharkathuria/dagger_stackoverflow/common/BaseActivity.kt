package com.tusharkathuria.dagger_stackoverflow.common

import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.DaggerActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Injector
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {
    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this, (application as MyApplication).appComponent)).build()
    }
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule(activityComponent)).build()
    }
    protected val injector get() = Injector(presentationComponent)
}