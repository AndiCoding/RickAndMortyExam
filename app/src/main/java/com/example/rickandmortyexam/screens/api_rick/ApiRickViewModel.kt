package com.example.rickandmortyexam.screens.api_rick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyexam.data.data_classes.RickAndMortyCharacter
import com.example.rickandmortyexam.data.services.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ApiRickViewModel : ViewModel() {
    private val _characterRepository : CharacterRepository = CharacterRepository


    val characters = MutableStateFlow<List<RickAndMortyCharacter>>(emptyList())

    fun getAllCharacters() {
        viewModelScope.launch {
            characters.value = _characterRepository.getAllCharacters()
        }
    }


}