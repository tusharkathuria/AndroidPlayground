package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailUI

open class BaseUI<LISTENER_TYPE>(private val layoutInflater: LayoutInflater, private val viewGroup: ViewGroup?, @LayoutRes private val layoutRes: Int) {
    val rootView = layoutInflater.inflate(layoutRes, viewGroup, false)
    protected val context: Context
        get() = rootView.context
    protected val listeners = HashSet<LISTENER_TYPE>()

    protected fun <T: View?> findViewById(@IdRes id: Int) : T = rootView.findViewById(id)

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}