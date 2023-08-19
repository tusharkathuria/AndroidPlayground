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
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
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

class QuestionDetailsActivity : AppCompatActivity(), QuestionDetailUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var stackoverflowApi: StackoverflowApi
    private lateinit var questionId: String
    private lateinit var questionDetailUI: QuestionDetailUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailUI = QuestionDetailUI(layoutInflater, null)
        setContentView(questionDetailUI.rootView)

        // init retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

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
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question.body
                    questionDetailUI.setQuestionText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY))
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            } finally {
                questionDetailUI.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
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
        onBackPressed()
    }
}