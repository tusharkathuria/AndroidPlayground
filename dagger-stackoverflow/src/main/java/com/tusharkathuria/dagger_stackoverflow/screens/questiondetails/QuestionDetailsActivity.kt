package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import com.tusharkathuria.dagger_stackoverflow.common.BaseActivity
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var questionId: String
    private lateinit var questionDetailUI: QuestionDetailUI
    @Inject lateinit var fetchQuestionDetailUseCase: FetchQuestionDetailUseCase
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var uiFactory: UIFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        questionDetailUI = uiFactory.newQuestionDetailUI(null)
        setContentView(questionDetailUI.rootView)
        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails()
        questionDetailUI.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        questionDetailUI.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            questionDetailUI.showProgressIndication()
            try {
                when (val result = fetchQuestionDetailUseCase.fetch(questionId)) {
                    is FetchQuestionDetailUseCase.Result.Success -> {
                        questionDetailUI.setQuestionText(
                            Html.fromHtml(
                                result.question.body,
                                Html.FROM_HTML_MODE_LEGACY
                            )
                        )
                    }

                    is FetchQuestionDetailUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questionDetailUI.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackPressUIEvent() {
        screensNavigator.navigateBack()
    }
}