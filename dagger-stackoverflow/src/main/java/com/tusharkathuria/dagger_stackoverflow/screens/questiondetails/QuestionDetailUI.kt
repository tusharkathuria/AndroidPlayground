package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.common.image_loader.ImageLoader
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import com.tusharkathuria.dagger_stackoverflow.questions.QuestionWithBody
import com.tusharkathuria.dagger_stackoverflow.screens.common.BaseUI
import com.tusharkathuria.dagger_stackoverflow.screens.common.toolbar.MyToolbar

class QuestionDetailUI(layoutInflater: LayoutInflater, parent: ViewGroup?, private val imageLoader: ImageLoader): BaseUI<QuestionDetailUI.Listener>(layoutInflater, parent, R.layout.layout_question_details) {

    private var toolbar: MyToolbar = findViewById(R.id.toolbar)
    private var swipeRefresh: SwipeRefreshLayout
    private var txtQuestionBody: TextView = findViewById(R.id.txt_question_body)
    private val imgUser: ImageView = findViewById(R.id.img_user)
    private val txtUserName: TextView = findViewById(R.id.txt_user_name)

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

    fun setQuestion(question: QuestionWithBody) {
        txtQuestionBody.text = Html.fromHtml(
            question.body,
            Html.FROM_HTML_MODE_LEGACY
        )
        imageLoader.loadImage(question.owner.imageUrl, imgUser)
        txtUserName.text = question.owner.name
    }

    interface Listener {
        fun onBackPressUIEvent()
    }
}