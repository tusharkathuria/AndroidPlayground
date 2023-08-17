package com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.view

import com.tusharkathuria.androidplayground.coroutines.fetch_country_list_using_retrofit.model.Country

data class ListUIState(
    val list: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)