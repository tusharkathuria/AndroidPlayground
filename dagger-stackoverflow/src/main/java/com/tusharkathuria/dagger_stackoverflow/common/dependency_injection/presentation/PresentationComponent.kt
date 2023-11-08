package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation

import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionsListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class, UseCasesModule::class])
interface PresentationComponent {
    fun inject(questionsListFragment: QuestionsListFragment)
    fun inject(questionDetailsActivity: QuestionDetailsActivity)
}