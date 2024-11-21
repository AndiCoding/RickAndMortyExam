package com.example.rickandmortyexam.screens.show_rick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowRickViewModel: ViewModel() {

    private val _characters = MutableStateFlow<List<RoomRMCharacter>>(emptyList())
    val character = _characters.asStateFlow()

    private var allCharacters = listOf<RoomRMCharacter>()


    fun getCharacter(){
        viewModelScope.launch (Dispatchers.IO) {
            allCharacters = CharacterDatabaseRepository.getDatabaseCharacters().map { character ->
                character.copy(status = character.status.lowercase())
            }
            _characters.value = CharacterDatabaseRepository.getDatabaseCharacters()

        }
    }
    fun showAliveCharacters(){
        viewModelScope.launch (Dispatchers.IO) {
            _characters.value = allCharacters.filter { it.status.equals("alive", ignoreCase = true) }
        }
    }
    fun showDeadCharacters(){
        viewModelScope.launch (Dispatchers.IO) {
            _characters.value = allCharacters.filter { it.status.equals("dead", ignoreCase = true) }
        }
    }
}

