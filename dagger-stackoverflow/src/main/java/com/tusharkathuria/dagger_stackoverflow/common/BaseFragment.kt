package com.tusharkathuria.dagger_stackoverflow.common

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot
}