package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation

import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun uiFactory(): UIFactory
    fun screensNavigator(): ScreensNavigator
    fun dialogsNavigator(): DialogsNavigator
    fun fetchQuestionsUseCase(): FetchQuestionsUseCase
    fun fetchQuestionDetailsUseCase(): FetchQuestionDetailUseCase
}