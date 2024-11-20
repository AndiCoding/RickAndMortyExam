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

    private val _characters = MutableStateFlow<List<RoomRMCharacter>>(emptyList()) //can be modified
    val character = _characters.asStateFlow()


    fun setCharacter(){
        viewModelScope.launch (Dispatchers.IO) {
            _characters.value = CharacterDatabaseRepository.getDatabaseCharacters()
        }
    }
}

