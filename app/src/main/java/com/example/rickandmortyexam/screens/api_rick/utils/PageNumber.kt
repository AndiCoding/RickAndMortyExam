package com.example.rickandmortyexam.screens.api_rick.utils

import com.example.rickandmortyexam.data.data_classes.Info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object PageNumber {
    private val _pageNumber = MutableStateFlow("")
    val pageNumber: StateFlow<String> = _pageNumber.asStateFlow()

    fun getString (
        pageInfo: StateFlow<Info?>,
    ){
        val totalPages = pageInfo.value?.pages
        val nextPage = pageInfo.value?.next ?: totalPages

        // extracts the "page=17" (i.e.) from  next url,
        val regex = "page=(\\d+)".toRegex()
        val matchResult = regex.find(nextPage.toString())
        val currentPage = (matchResult?.groups?.get(1)?.value?.toInt()?.minus(1))

        _pageNumber.value = "$currentPage / $totalPages"
    }
}