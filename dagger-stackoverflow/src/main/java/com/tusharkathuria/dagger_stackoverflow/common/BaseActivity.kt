package com.tusharkathuria.dagger_stackoverflow.common

import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.ActivityCompositionRoot
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Injector
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.PresentationModule

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
    val activityCompositionRoot by lazy { ActivityCompositionRoot(this, appCompositionRoot) }
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule(activityCompositionRoot)).build()
    }
    protected val injector get() = Injector(presentationComponent)
}