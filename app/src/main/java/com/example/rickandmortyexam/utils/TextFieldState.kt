package com.example.rickandmortyexam.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// TextFieldState is a singleton object
// that holds the state of the TextField
// in the ApiRickScreen

object TextFieldState {
    val _statusText = MutableStateFlow("")
    val statusText: StateFlow<String> = _statusText.asStateFlow()

    val _nameText = MutableStateFlow("")
    val nameText: StateFlow<String> = _nameText.asStateFlow()

    fun setNameText(name: String) {
        _nameText.value = name
    }
    fun setStatusText(status: String) {
        _statusText.value = status
    }
}