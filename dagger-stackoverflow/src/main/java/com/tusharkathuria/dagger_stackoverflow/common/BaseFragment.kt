package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Injector
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.PresentationModule

open class BaseFragment: Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot)).build()
    }
    protected val injector get() = Injector(presentationComponent)
}