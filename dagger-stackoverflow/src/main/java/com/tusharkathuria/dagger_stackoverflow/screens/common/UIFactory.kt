package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import com.tusharkathuria.dagger_stackoverflow.common.image_loader.ImageLoader
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailUI
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionListUI
import javax.inject.Inject

class UIFactory @Inject constructor(private val layoutInflater: LayoutInflater, private val imageLoader: ImageLoader) {
    fun newQuestionListUI(parent: ViewGroup?): QuestionListUI = QuestionListUI(layoutInflater, parent)
    fun newQuestionDetailUI(parent: ViewGroup?): QuestionDetailUI = QuestionDetailUI(layoutInflater, parent, imageLoader)
}