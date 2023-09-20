package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import android.view.LayoutInflater
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import androidx.fragment.app.FragmentManager;
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityCompositionRoot: ActivityCompositionRoot) {
    @Provides
    fun stackoverflowApi(): StackoverflowApi = activityCompositionRoot.stackoverflowApi
    @Provides
    fun fragmentManager(): FragmentManager = activityCompositionRoot.fragmentManager
    @Provides
    fun layoutInflater(): LayoutInflater = activityCompositionRoot.layoutInflater
    @Provides
    fun screensNavigator() = activityCompositionRoot.screensNavigator
    @Provides
    fun uiFactory(layoutInflater: LayoutInflater) = UIFactory(layoutInflater)
    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)
    @Provides
    fun fetchQuestionDetailUseCase(stackoverflowApi: StackoverflowApi)  = FetchQuestionDetailUseCase(stackoverflowApi)
    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionsUseCase(stackoverflowApi)
}