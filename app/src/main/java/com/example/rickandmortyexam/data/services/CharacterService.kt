package com.example.rickandmortyexam.data.services

import com.example.rickandmortyexam.data.data_classes.ApiRMCharacterList
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): Response<ApiRMCharacterList>

}