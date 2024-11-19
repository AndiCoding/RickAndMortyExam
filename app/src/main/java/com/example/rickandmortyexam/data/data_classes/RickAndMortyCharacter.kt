package com.example.rickandmortyexam.data.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RickAndMortyCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // por defecto es 0, "seed" for id
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?
)
