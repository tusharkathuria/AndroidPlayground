package com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.model.CountriesService
import com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.model.Country
import com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.view.ListUIState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ListViewModel: ViewModel() {
    val uiState = MutableStateFlow(ListUIState())
    val countriesClient = CountriesService.getService()
    val countriesCallExceptionHandler = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
        uiState.value = uiState.value.copy(error = throwable.localizedMessage, isLoading = false)
    }

    fun refreshCountries() {
        uiState.value = uiState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO + countriesCallExceptionHandler) {
            val response: Response<List<Country>> = countriesClient.getCountries()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    uiState.value = uiState.value.copy(list = response.body() ?: emptyList(), error = null, isLoading = false)
                } else {
                    uiState.value = uiState.value.copy(error = response.message(), isLoading = false)
                }
            }
        }
    }
}