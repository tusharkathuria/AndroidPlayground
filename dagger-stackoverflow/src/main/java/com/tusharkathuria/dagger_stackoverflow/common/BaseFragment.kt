package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.DaggerPresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule

open class BaseFragment: Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent((requireActivity() as BaseActivity).activityComponent)
            .presentationModule(PresentationModule()).build()
    }
    protected val injector get() = presentationComponent
}