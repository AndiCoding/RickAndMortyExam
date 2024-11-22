package com.example.rickandmortyexam.screens.api_rick.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object TextFieldState {
    val _statusText = MutableStateFlow("")
    val statusText: StateFlow<String> = _statusText.asStateFlow()

    val _nameText = MutableStateFlow("")
    val nameText: StateFlow<String> = _nameText.asStateFlow()


}