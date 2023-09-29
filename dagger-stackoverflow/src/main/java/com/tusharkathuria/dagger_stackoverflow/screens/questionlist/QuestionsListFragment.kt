package com.tusharkathuria.dagger_stackoverflow.screens.questionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tusharkathuria.dagger_stackoverflow.common.BaseFragment
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsListFragment : BaseFragment(), QuestionListUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var isDataLoaded = false
    private lateinit var questionListUI: QuestionListUI
    @Inject lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var uiFactory: UIFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        questionListUI = uiFactory.newQuestionListUI(container)
        return questionListUI.rootView
    }

    override fun onStart() {
        super.onStart()
        if (!isDataLoaded) {
            fetchQuestions()
        }
        questionListUI.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        questionListUI.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            questionListUI.showProgressIndication()

            try {
                val result = fetchQuestionsUseCase.fetch()
                when (result) {
                    is FetchQuestionsUseCase.Result.Success -> {
                        questionListUI.bindQuestions(result.questions)
                        isDataLoaded = true
                    }

                    is FetchQuestionsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questionListUI.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onRefreshUIEvent() {
        fetchQuestions()
    }

    override fun onQuestionClickedUIEvent(question: Question) {
        screensNavigator.navigateToQuestionDetails(question.id)
    }
}