package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionsListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {
    fun inject(questionDetailsActivity: QuestionDetailsActivity) {
        questionDetailsActivity.screensNavigator = compositionRoot.screensNavigator
        questionDetailsActivity.dialogsNavigator = compositionRoot.dialogsNavigator
        questionDetailsActivity.fetchQuestionDetailUseCase = compositionRoot.fetchQuestionDetailUseCase
        questionDetailsActivity.uiFactory = compositionRoot.uiFactory
    }

    fun inject(questionsListFragment: QuestionsListFragment) {
        questionsListFragment.dialogsNavigator = compositionRoot.dialogsNavigator
        questionsListFragment.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        questionsListFragment.screensNavigator = compositionRoot.screensNavigator
        questionsListFragment.uiFactory = compositionRoot.uiFactory
    }
}