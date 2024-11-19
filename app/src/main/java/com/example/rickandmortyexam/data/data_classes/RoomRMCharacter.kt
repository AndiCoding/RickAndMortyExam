package com.example.rickandmortyexam.data.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character_table")
data class RoomRMCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?
)
