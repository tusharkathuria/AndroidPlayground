package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi
    private val fragmentManager get() = activityCompositionRoot.fragmentManager
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val uiFactory get() = UIFactory(layoutInflater)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    val fetchQuestionDetailUseCase get()  = FetchQuestionDetailUseCase(stackoverflowApi)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
}