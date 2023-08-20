package com.tusharkathuria.dagger_stackoverflow.screens.questionlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsListActivity : AppCompatActivity(), QuestionListUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var stackoverflowApi: StackoverflowApi
    private var isDataLoaded = false
    private lateinit var questionListUI: QuestionListUI
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionListUI = QuestionListUI(layoutInflater, null)
        setContentView(questionListUI.rootView)

        fetchQuestionsUseCase = (application as MyApplication).fetchQuestionsUseCase
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
        screensNavigator = ScreensNavigator(this)
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