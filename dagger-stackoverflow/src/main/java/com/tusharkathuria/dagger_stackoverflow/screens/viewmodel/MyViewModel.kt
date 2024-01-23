package com.tusharkathuria.dagger_stackoverflow.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tusharkathuria.dagger_stackoverflow.questions.Question
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class MyViewModel @Inject constructor(
    private val fetchQuestionsUseCase: FetchQuestionsUseCase
): ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    init {
        viewModelScope.launch {
            val result = fetchQuestionsUseCase.fetch()
            if (result is FetchQuestionsUseCase.Result.Success) {
                _questions.value = result.questions
            } else {
                throw RuntimeException("fetch failed")
            }
        }
    }

    class Factory @Inject constructor(private val fetchQuestionsUseCaseProvider: Provider<FetchQuestionsUseCase>): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyViewModel(fetchQuestionsUseCaseProvider.get()) as T
        }
    }
}