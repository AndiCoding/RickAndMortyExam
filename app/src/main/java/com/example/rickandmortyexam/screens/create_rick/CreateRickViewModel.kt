package com.example.rickandmortyexam.screens.create_rick

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException

class CreateRickViewModel: ViewModel() {
    private val _createdCharacter = MutableStateFlow(RoomRMCharacter())

     fun insertCharacter(character: RoomRMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newCharId = CharacterDatabaseRepository.insertCharacter(character)
                if (newCharId != -1L) {
                    _createdCharacter.value = character.copy(id = newCharId.toInt())
                }
                println(newCharId)
            } catch( e: SQLException) {
                Log.e("SQL Error", "Error inserting character", e)
            }
            catch (e: Exception) {
                Log.e("Unknown Error", "Error inserting character", e)
            }
        }
    }

    fun getCreatedCharacter() = _createdCharacter
}


