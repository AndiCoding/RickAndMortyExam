package com.example.rickandmortyexam.data.services

import com.example.rickandmortyexam.data.data_classes.ApiRMCharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET
    suspend fun getCharacters(@Url url: String): Response<ApiRMCharacterList>

}