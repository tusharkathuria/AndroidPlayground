package com.tusharkathuria.dagger_stackoverflow.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.Constants
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.common.BaseActivity
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.ServerErrorDialogFragment
import com.tusharkathuria.dagger_stackoverflow.screens.common.toolbar.MyToolbar
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDetailsActivity : BaseActivity(), QuestionDetailUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var stackoverflowApi: StackoverflowApi
    private lateinit var questionId: String
    private lateinit var questionDetailUI: QuestionDetailUI
    private lateinit var fetchQuestionDetailUseCase: FetchQuestionDetailUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailUI = QuestionDetailUI(layoutInflater, null)
        setContentView(questionDetailUI.rootView)
        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!

        fetchQuestionDetailUseCase = compositionRoot.fetchQuestionDetailUseCase
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
        screensNavigator = ScreensNavigator(this)
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