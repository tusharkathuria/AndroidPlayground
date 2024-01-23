package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation

import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionsListFragment
import com.tusharkathuria.dagger_stackoverflow.screens.viewmodel.ViewModelActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent()
interface PresentationComponent {
    fun inject(questionsListFragment: QuestionsListFragment)
    fun inject(questionDetailsActivity: QuestionDetailsActivity)
    fun inject(viewModelActivity: ViewModelActivity)
}