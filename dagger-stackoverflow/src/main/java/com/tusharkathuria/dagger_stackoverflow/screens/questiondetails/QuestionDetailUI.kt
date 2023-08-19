package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.screens.common.toolbar.MyToolbar

class QuestionDetailUI(layoutInflater: LayoutInflater, parent: ViewGroup?) {

    val rootView: View
    private var toolbar: MyToolbar
    private var swipeRefresh: SwipeRefreshLayout
    private var txtQuestionBody: TextView
    private val listeners = HashSet<Listener>()

    init {
        rootView = layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
        txtQuestionBody = findViewById(R.id.txt_question_body)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            listeners.forEach {
                it.onBackPressUIEvent()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }

    private fun <T: View?> findViewById(@IdRes id: Int) : T = rootView.findViewById(id)

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    fun setQuestionText(text: CharSequence) {
        txtQuestionBody.text = text
    }

    interface Listener {
        fun onBackPressUIEvent()
    }
}