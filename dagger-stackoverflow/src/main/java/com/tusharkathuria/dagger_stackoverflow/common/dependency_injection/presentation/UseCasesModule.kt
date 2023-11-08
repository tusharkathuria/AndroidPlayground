package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation

import android.view.LayoutInflater
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import androidx.fragment.app.FragmentManager;
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun fetchQuestionDetailUseCase(stackoverflowApi: StackoverflowApi)  = FetchQuestionDetailUseCase(stackoverflowApi)
    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionsUseCase(stackoverflowApi)
}