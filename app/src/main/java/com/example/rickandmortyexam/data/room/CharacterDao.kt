package com.example.rickandmortyexam.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter

@Dao // Data Access Object, interface que define las operaciones q se pueden hacer con la base de datos
interface CharacterDao {
    @Query("SELECT * FROM character_table")
    suspend fun getCharacters(): List<RoomRMCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: RoomRMCharacter): Long // returns the id of the inserted character

}