package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.Injector
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.PresentationCompositionRoot

open class BaseFragment: Fragment() {
    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
    protected val injector get() = Injector(compositionRoot)
}