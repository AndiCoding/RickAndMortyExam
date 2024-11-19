package com.example.rickandmortyexam.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import java.sql.SQLException

object CharacterDatabaseRepository {
    private lateinit var _characterDatabase: CharacterDatabase // variable que alamacena los datos en la database
    private val _characterDao by lazy { _characterDatabase.characterDao()}//recogemos las operaciones del dao y las almacenamos en una variable


    //Initialize the database
    fun initializeDatabase(context: Context){
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            name = "character_database"
        ).fallbackToDestructiveMigration().build()
    }

    //Get all characters
    suspend fun getDatabaseCharacters(): List<RoomRMCharacter>{
        try {
            return _characterDao.getCharacters()

        }catch (e: SQLException){
            Log.e("DatabaseError", e.toString())
            return emptyList()

        }catch (e: Exception){
            Log.e("OtherError", e.toString())
            return emptyList()
    }
    }
    // insert a new character
    suspend fun insertCharacter(character: RoomRMCharacter): Long{
        try {
            return _characterDao.insertCharacter(character)
        }catch (e:SQLException){
            Log.e("DatabaseError", e.toString())
            return -1L
        }
    }
}