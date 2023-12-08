package com.tusharkathuria.dagger_stackoverflow.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tusharkathuria.dagger_stackoverflow.common.image_loader.ImageLoader
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailUI
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionListUI
import javax.inject.Inject
import javax.inject.Provider

class UIFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val imageLoaderProvider: Provider<ImageLoader>
    // Using Provider ensures that object is provided while respecting conventions like scopes in the DI graph. This helps in late injection
) {
    fun newQuestionListUI(parent: ViewGroup?): QuestionListUI = QuestionListUI(layoutInflater, parent)
    fun newQuestionDetailUI(parent: ViewGroup?): QuestionDetailUI = QuestionDetailUI(layoutInflater, parent, imageLoaderProvider.get())
}