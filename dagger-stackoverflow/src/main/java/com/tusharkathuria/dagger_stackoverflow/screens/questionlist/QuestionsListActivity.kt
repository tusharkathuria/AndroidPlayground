package com.tusharkathuria.dagger_stackoverflow.screens.questionlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.Constants
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.ServerErrorDialogFragment
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : AppCompatActivity(), QuestionListUI.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var stackoverflowApi: StackoverflowApi
    private var isDataLoaded = false
    private lateinit var questionListUI: QuestionListUI
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionListUI = QuestionListUI(layoutInflater, null)
        setContentView(questionListUI.rootView)

        // init retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        stackoverflowApi = retrofit.create(StackoverflowApi::class.java)
        fetchQuestionsUseCase = FetchQuestionsUseCase()
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
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    override fun onRefreshUIEvent() {
        fetchQuestions()
    }

    override fun onQuestionClickedUIEvent(question: Question) {
        QuestionDetailsActivity.start(this, question.id)
    }
}