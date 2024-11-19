package com.example.rickandmortyexam.data.room

import android.content.Context
import androidx.room.Room

object CharacterDatabaseRepository {
    private lateinit var _characterDatabase: CharacterDatabase // variable que alamacena los datos en la database
    private val _characterDao by lazy { _characterDatabase.characterDao()}//recogemos las operaciones del dao y las almacenamos en una variable

    //Initialize the database
    fun initializeDatabase(context: Context){
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            name = "character_database"
        ).build()
    }
    
}