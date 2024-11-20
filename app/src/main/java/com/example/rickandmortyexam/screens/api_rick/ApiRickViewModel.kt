package com.example.rickandmortyexam.screens.api_rick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import com.example.rickandmortyexam.data.services.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ApiRickViewModel : ViewModel() {

    // Repository object with connection to the API
    private val _characterRepository : CharacterRepository = CharacterRepository

    // List of characters gathered from the API. the list is overwritten
    // with new characters when the next or previous page is fetched.

    val characters = MutableStateFlow<List<ApiRMCharacter>>(emptyList())

    // metadata about the current page of characters.
    // Contains info about next and prev page

    private var pageInfo: Info? = null


    // Fetches the first page of characters from the API when the view model is created.
    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    fun nextCharacters() {
        viewModelScope.launch {
            pageInfo?.next?.let { getCharacters(it) }

        }
    }
    fun prevCharacters() {
        viewModelScope.launch {
            pageInfo?.prev?.let { getCharacters(it) }
        }
    }

    private suspend fun getCharacters(url: String = "character") {
        val (characters, info) = _characterRepository.getCharacters(url)
        this.characters.value = characters
        pageInfo = info
    }

}


/*
* if (pageInfo?.next != null) {
                getCharacters(pageInfo?.next ?: "")}
            if (pageInfo?.prev != null) {
                getCharacters(pageInfo?.prev ?: "")
            }
*
* */