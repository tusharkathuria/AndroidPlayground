package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailUI
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionListUI

class UIFactory(private val layoutInflater: LayoutInflater) {
    fun newQuestionListUI(parent: ViewGroup?): QuestionListUI = QuestionListUI(layoutInflater, parent)
    fun newQuestionDetailUI(parent: ViewGroup?): QuestionDetailUI = QuestionDetailUI(layoutInflater, parent)
}