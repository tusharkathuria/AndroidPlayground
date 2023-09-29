package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Injector
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule

open class BaseFragment: Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule((requireActivity() as BaseActivity).activityComponent)).build()
    }
    protected val injector get() = Injector(presentationComponent)
}