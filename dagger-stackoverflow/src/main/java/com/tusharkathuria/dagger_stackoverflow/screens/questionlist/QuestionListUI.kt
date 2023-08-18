package com.tusharkathuria.dagger_stackoverflow.screens.questionlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tusharkathuria.dagger_stackoverflow.R
import com.tusharkathuria.dagger_stackoverflow.questions.Question

class QuestionListUI(layoutInflater: LayoutInflater, parent: ViewGroup?) {

    private var swipeRefresh: SwipeRefreshLayout
    private var recyclerView: RecyclerView
    private var questionsAdapter: QuestionsAdapter
    private val listeners = HashSet<Listener>()
    private val context: Context
        get() = rootView.context
    val rootView: View

    init {
        rootView = layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            listeners.forEach {
                it.onRefreshUIEvent()
            }
        }

        // init recycler view
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        questionsAdapter = QuestionsAdapter{ clickedQuestion ->
            listeners.forEach {
                it.onQuestionClickedUIEvent(clickedQuestion)
            }
        }
        recyclerView.adapter = questionsAdapter
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindData(questions)
    }

    private fun <T: View?> findViewById(@IdRes id: Int) : T = rootView.findViewById(id)

    interface Listener {
        fun onRefreshUIEvent()
        fun onQuestionClickedUIEvent(question: Question)
    }

    class QuestionsAdapter(
        private val onQuestionClickListener: (Question) -> Unit
    ) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

        private var questionsList: List<Question> = ArrayList(0)

        inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(questions: List<Question>) {
            questionsList = ArrayList(questions)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_question_list_item, parent, false)
            return QuestionViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            holder.title.text = questionsList[position].title
            holder.itemView.setOnClickListener {
                onQuestionClickListener.invoke(questionsList[position])
            }
        }

        override fun getItemCount(): Int {
            return questionsList.size
        }
    }
}