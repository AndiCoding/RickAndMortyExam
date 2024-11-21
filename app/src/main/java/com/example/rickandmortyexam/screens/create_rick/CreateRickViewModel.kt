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

    val createdCharacter = MutableStateFlow(RoomRMCharacter())

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    fun getStatusText(): String {
        return createdCharacter.value.status
    }


    fun updateCharacterField(fieldName: String, value: String) {
        createdCharacter.value = when (fieldName) {
            "name" -> createdCharacter.value.copy(name = value)
            "status" -> createdCharacter.value.copy(status = value)
            "species" -> createdCharacter.value.copy(species = value)
            "type" -> createdCharacter.value.copy(type = value)
            "gender" -> createdCharacter.value.copy(gender = value)
            else -> createdCharacter.value
        }
    }

     fun insertCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharId = CharacterDatabaseRepository.insertCharacter(createdCharacter.value)
            if (newCharId != -1L) {
                createdCharacter.value = RoomRMCharacter()
                _message.value = "Character created successfully!"

            } else {
                _message.value = "Error inserting character"
            }
        }
    }


    fun clearMessage() {
        _message.value = null
    }

}


