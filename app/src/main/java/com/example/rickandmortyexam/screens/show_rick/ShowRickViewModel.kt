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

    //init block to get the characters from the database when the viewmodel is created
    init {
        getCharacter()
    }


    //function to get the characters from the database
    // turns their status to lowercase, so easier to compare
    // if the user types in uppercase
    fun getCharacter(){
        viewModelScope.launch (Dispatchers.IO) {
            _characters.value = CharacterDatabaseRepository.getDatabaseCharacters()
            allCharacters = _characters.value.map { character ->
                character.copy(status = character.status.lowercase())
            }

        }
    }


    // filters character by status
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

