package com.example.rickandmortyexam.screens.create_rick

import androidx.lifecycle.ViewModel
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateRickViewModel: ViewModel() {

    // current character being created
    val createdCharacter = MutableStateFlow(RoomRMCharacter())

    // Message that can be displayed to the user
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    fun getStatusText(): String {
        return createdCharacter.value.status
    }

    // handles user event for each input field
    fun updateCharacterField(fieldName: String, value: String) {
        createdCharacter.value = when (fieldName) {
            "name" -> createdCharacter.value.copy(name = value)
            "status" -> createdCharacter.value.copy(status = value)
            "species" -> createdCharacter.value.copy(species = value)
            else -> createdCharacter.value
        }
    }

    // Inserting character into the database,
    // if all fields are filled in and the database returns
    // a positive number (the id of the new character in the DB)
     fun insertCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            if(createdCharacter.value.name.isEmpty() || createdCharacter.value.status.isEmpty() || createdCharacter.value.species.isEmpty()) {
                _message.value = "Please fill in all fields"
            } else {
                val newCharId = CharacterDatabaseRepository.insertCharacter(createdCharacter.value)

                if (newCharId != -1L) {
                    createdCharacter.value = RoomRMCharacter()
                    _message.value = "Character created successfully!"
                } else {
                    _message.value = "Error inserting character"
                }
            }

            CharacterDatabaseRepository.getDatabaseCharacters()
        }
    }

    fun deleteAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            CharacterDatabaseRepository.deleteAllCharacters()
            _message.value = "All characters deleted successfully!"
        }

    }

    fun clearMessage() {
        _message.value = null
    }

}


