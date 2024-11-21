package com.example.rickandmortyexam.screens.api_rick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import com.example.rickandmortyexam.data.services.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiRickViewModel : ViewModel() {

    // Repository object with connection to the API
    private val _characterRepository : CharacterRepository = CharacterRepository

    // List of characters gathered from the API. the list is overwritten
    // with new characters when the next or previous page is fetched.

    val characters = MutableStateFlow<List<ApiRMCharacter>>(emptyList())

    // metadata about the current page of characters.
    // Contains info about next and prev page

    private val _pageInfo = MutableStateFlow<Info?>(null)
    val pageInfo = _pageInfo.asStateFlow()

    private val _searchResult = MutableStateFlow<String>("")
    val searchResult = _searchResult.asStateFlow()

    private fun resultStringBuilder() {
        val resultCount = pageInfo.value?.count ?: 0
        val name = nameText.value
        val status = statusText.value

        val finalString = buildString {
            append("There are $resultCount characters")
            if (name.isNotEmpty()) {
                append(" named '$name'")
            }
            if (status.isNotEmpty()) {
                append(" with status '$status'")
            }
        }

        _searchResult.value = finalString
    }


    private val _nameText = MutableStateFlow("")
    val nameText = _nameText.asStateFlow()

    fun setNameText(name: String) {
        _nameText.value = name
    }


    private val _statusText = MutableStateFlow("")
    val statusText = _statusText.asStateFlow()

    fun setStatusText(status: String) {
        _statusText.value = status
    }

    // Fetches the first page of characters from the API when the view model is created.
    init {
        viewModelScope.launch {
            getCharacters()
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

    private suspend fun getCharacters(url: String = "character") {
        val (characters, info) = _characterRepository.getCharacters(url)
        this.characters.value = characters
        _pageInfo.value = info
        resultStringBuilder()
    }

    // function called when user presses the search button
    fun searchCharacters(name: String, status: String) {
        var url = "character/?"

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