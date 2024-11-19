package com.example.rickandmortyexam.screens.create_rick
import androidx.lifecycle.ViewModel
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateRickViewModel: ViewModel() {
    private val _characters = MutableStateFlow<List<RoomRMCharacter>>(emptyList()) //can be modified
    val character = _characters.asStateFlow()

    fun setCharacter(){
        viewModelScope.launch (Dispatchers.IO) {
            _characters.value = CharacterDatabaseRepository.getDatabaseCharacters()
        }
    }

    fun insertCharacter(character: RoomRMCharacter){
        viewModelScope.launch(Dispatchers.IO) {
            val  newCharId = CharacterDatabaseRepository.insertCharacter(character)
            if (newCharId != -1L) {
                val newCharacter = character.copy(id = newCharId.toInt())
                _characters.value += newCharacter
            }
        }
    }
}