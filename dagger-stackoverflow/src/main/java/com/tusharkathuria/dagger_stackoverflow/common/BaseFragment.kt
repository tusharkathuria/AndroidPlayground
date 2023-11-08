package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }
    protected val injector get() = presentationComponent
}