package com.example.rickandmortyexam.screens.create_rick

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyexam.data.services.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateRickViewModel {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val character = _characters.asStateFlow()

    /*fun addCharacter(){
        viewModelScope.launch (Dispatchers.IO){
            _characters.value = CharacterRepository.getCharacters()//
        }//

    }*/

}