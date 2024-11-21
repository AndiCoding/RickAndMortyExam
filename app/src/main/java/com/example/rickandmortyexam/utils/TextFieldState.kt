package com.example.rickandmortyexam.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// TextFieldState is a singleton object
// that holds the state of the TextField
// in the ApiRickScreen

object TextFieldState {
    private val updateStatusText = MutableStateFlow("")
    val statusText: StateFlow<String> = updateStatusText.asStateFlow()

    private val updateNameText = MutableStateFlow("")
    val nameText: StateFlow<String> = updateNameText.asStateFlow()

    fun setNameText(name: String) {
        updateNameText.value = name
    }
    fun setStatusText(status: String) {
        updateStatusText.value = status
    }
}