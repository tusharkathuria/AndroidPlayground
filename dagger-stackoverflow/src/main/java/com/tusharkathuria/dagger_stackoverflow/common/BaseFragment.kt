package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
}