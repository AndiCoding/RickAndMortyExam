package com.example.rickandmortyexam.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyexam.data.data_classes.RickAndMortyCharacter

@Dao // Data Access Object, interface que define las operaciones q se pueden hacer con la base de datos
interface CharacterDao {
    @Query("SELECT * FROM RickAndMortyCharacter")
    suspend fun getCharacters(): List<RickAndMortyCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: RickAndMortyCharacter): Long // returns the id of the inserted character

}