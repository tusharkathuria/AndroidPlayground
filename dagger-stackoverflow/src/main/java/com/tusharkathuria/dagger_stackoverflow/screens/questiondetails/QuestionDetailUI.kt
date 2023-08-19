package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.screens.common.BaseUI
import com.tusharkathuria.dagger_stackoverflow.screens.common.toolbar.MyToolbar

class QuestionDetailUI(layoutInflater: LayoutInflater, parent: ViewGroup?): BaseUI<QuestionDetailUI.Listener>(layoutInflater, parent, R.layout.layout_question_details) {

    private var toolbar: MyToolbar = findViewById(R.id.toolbar)
    private var swipeRefresh: SwipeRefreshLayout
    private var txtQuestionBody: TextView = findViewById(R.id.txt_question_body)

    init {

        // init toolbar
        toolbar.setNavigateUpListener {
            listeners.forEach {
                it.onBackPressUIEvent()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
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