package com.example.rickandmortyexam.screens.api_rick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import com.example.rickandmortyexam.data.services.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ApiRickViewModel : ViewModel() {
    private val _characterRepository : CharacterRepository = CharacterRepository

    val characters = MutableStateFlow<List<ApiRMCharacter>>(emptyList())

    private var pageInfo: Info? = null



    fun getInitialCharacters() {
        viewModelScope.launch {
                val (firstCharacters, info) = _characterRepository.getCharacters()
                characters.value = firstCharacters
                pageInfo = info
        }
    }

    fun nextCharacters() {
        viewModelScope.launch {
            if (pageInfo?.next != null) {
                val (nextCharacters, info) = _characterRepository.getCharacters(pageInfo?.next ?: "")
                characters.value = nextCharacters
                pageInfo = info}
        }
    }

    fun prevCharacters() {
        viewModelScope.launch {
            if (pageInfo?.prev != null) {
                val (prevCharacters, info) = _characterRepository.getCharacters(pageInfo?.prev ?: "")
                characters.value = prevCharacters
                pageInfo = info }
        }
    }

}