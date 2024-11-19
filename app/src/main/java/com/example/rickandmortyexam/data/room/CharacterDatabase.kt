package com.example.rickandmortyexam.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyexam.data.data_classes.RickAndMortyCharacter


@Database(
    entities=[RickAndMortyCharacter::class],
    version=1,
    exportSchema=false
)


abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}