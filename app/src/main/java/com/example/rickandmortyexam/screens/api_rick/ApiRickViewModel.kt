package com.example.rickandmortyexam.screens.api_rick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import com.example.rickandmortyexam.data.services.CharacterRepository
import com.example.rickandmortyexam.utils.PageNumber
import com.example.rickandmortyexam.utils.ResultStringBuilder
import com.example.rickandmortyexam.utils.TextFieldState
import com.example.rickandmortyexam.utils.TextFieldState.nameText
import com.example.rickandmortyexam.utils.TextFieldState.statusText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiRickViewModel : ViewModel() {

    private val _characterRepository: CharacterRepository = CharacterRepository

    val characters = MutableStateFlow<List<ApiRMCharacter>>(emptyList())

    // Info holds metadata from current page
    // allows to navigate to next and previous pages
    private val _pageInfo = MutableStateFlow<Info?>(null)
    private val pageInfo = _pageInfo.asStateFlow()

    // Components, Seperation of concerns,
    // makes the code more readable
    val searchResult = ResultStringBuilder.searchResult
    val pageNumber = PageNumber.pageNumber
    val nameText = TextFieldState.nameText
    val statusText = TextFieldState.statusText

    fun setNameText(name: String) {
        TextFieldState.setNameText(name)
    }

    fun setStatusText(status: String) {
        TextFieldState.setStatusText(status)
    }


    // runs when the ViewModel is created
    init {
        viewModelScope.launch {
            getCharacters()
        }
    }


    // Method to quickly reset the characters and fields
    fun resetCharacters() {
        viewModelScope.launch {
            getCharacters()
            setNameText("")
            setNameText("")
        }
    }



    fun nextCharacters() {
        viewModelScope.launch {
            pageInfo.value?.next?.let { getCharacters(it) }

        }
    }

    fun prevCharacters() {
        viewModelScope.launch {
            pageInfo.value?.prev?.let { getCharacters(it) }
        }
    }

    // get characters from the API
    private suspend fun getCharacters(url: String = "character") {
        val (characters, info) = _characterRepository.getCharacters(url)
        this.characters.value = characters
        _pageInfo.value = info
        ResultStringBuilder.buildString(pageInfo, nameText, statusText)
        PageNumber.getString(pageInfo)
    }


    // function called when user presses the search button

    fun searchCharacters() {
        var url = "character/?"
        val name = nameText.value
        val status = statusText.value

        if (name.isNotBlank()) {
            url += "name=$name&"
        }
        if (status.isNotBlank()) {
            url += "status=$status&"
        }

        viewModelScope.launch {
            getCharacters(url)
        }
    }




}