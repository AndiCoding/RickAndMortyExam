package com.example.rickandmortyexam.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import java.sql.SQLException

object CharacterDatabaseRepository {
    private lateinit var _characterDatabase: CharacterDatabase
    private val _characterDao by lazy { _characterDatabase.characterDao()}


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
        return try {
             _characterDao.getCharacters()
        }catch (e: SQLException){
            Log.e("Database Error", "Error in database while getting characters", e)
            emptyList()
        } catch (e: Exception){
            Log.e("Unknown Error", "Unkonwn error getting characters", e)
            emptyList()
    }
    }

    // insert a new character, returns the id of the
    // new character, or -1 if there was an error
    // uncomment the throw SQLException() line to test the error handling in the view

    suspend fun insertCharacter(character: RoomRMCharacter): Long{
        return try {
            _characterDao.insertCharacter(character)
            //throw SQLException()
        } catch (e:SQLException){
            Log.e("Database Error", e.toString())
            -1L
        } catch (e: Exception){
            Log.e("Unknown Error", e.toString())
            -1L
        }
    }


}