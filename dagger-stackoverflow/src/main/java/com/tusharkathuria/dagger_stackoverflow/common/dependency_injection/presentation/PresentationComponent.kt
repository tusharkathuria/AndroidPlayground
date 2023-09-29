package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation

import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.QuestionsListFragment
import dagger.Component

@PresentationScope
@Component(dependencies = [ActivityComponent::class] ,modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(questionsListFragment: QuestionsListFragment)
    fun inject(questionDetailsActivity: QuestionDetailsActivity)
}