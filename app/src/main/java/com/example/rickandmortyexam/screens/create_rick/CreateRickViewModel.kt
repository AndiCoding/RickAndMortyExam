package com.example.rickandmortyexam.screens.create_rick

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateRickViewModel: ViewModel() {
    val createdCharacter = MutableStateFlow(RoomRMCharacter())

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


    // function that empties the createdCharacter field if successful insert
     fun insertCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharId = CharacterDatabaseRepository.insertCharacter(createdCharacter.value)
            if (newCharId != -1L) {
                //createdCharacter.value = createdCharacter.value.copy(id = newCharId.toInt())
                createdCharacter.value = RoomRMCharacter()
            } else {
                Log.e("CreateRickViewModel", "Error inserting character")
            }
        }
    }

}


