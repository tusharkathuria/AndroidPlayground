package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.UseCasesModule

open class BaseFragment: Fragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule(), UseCasesModule())
    }
    protected val injector get() = presentationComponent
}