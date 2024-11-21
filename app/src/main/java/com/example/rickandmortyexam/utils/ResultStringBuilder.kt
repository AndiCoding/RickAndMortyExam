
package com.example.rickandmortyexam.utils

import com.example.rickandmortyexam.data.data_classes.Info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ResultStringBuilder {
    private val _searchResult = MutableStateFlow("")
    val searchResult: StateFlow<String> = _searchResult.asStateFlow()

    fun buildString(
        pageInfo: StateFlow<Info?>,
        nameText: StateFlow<String>,
        statusText: StateFlow<String>
    ): String {
        val resultCount = pageInfo.value?.count ?: 0
        val name = nameText.value
        val status = statusText.value

        return buildString {
            append("There are $resultCount characters")
            if (name.isNotEmpty()) {
                append(" named '$name'")
            }
            if (status.isNotEmpty()) {
                append(" with status '$status'")
            }
        }.also {
            _searchResult.value = it
        }
    }
}