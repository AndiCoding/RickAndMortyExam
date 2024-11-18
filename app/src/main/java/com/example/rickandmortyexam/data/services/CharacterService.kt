package com.example.rickandmortyexam.data.services

import com.example.rickandmortyexam.data.data_classes.Character
import com.example.rickandmortyexam.data.data_classes.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterList>

}