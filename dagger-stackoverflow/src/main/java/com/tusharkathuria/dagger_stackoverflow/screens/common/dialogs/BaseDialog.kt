package com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.tusharkathuria.dagger_stackoverflow.common.BaseActivity

open class BaseDialog: DialogFragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }
    protected val injector get() = presentationComponent
}