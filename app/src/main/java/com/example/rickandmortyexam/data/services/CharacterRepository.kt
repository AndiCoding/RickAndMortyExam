package com.example.rickandmortyexam.data.services

import android.util.Log
import com.example.rickandmortyexam.data.data_classes.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterRepository {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val _okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        .build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _characterService = _retrofit
        .create(CharacterService::class.java)

    suspend fun getAllCharacters(): List<Character> {

        try {
            val response = _characterService.getCharacters()


            Log.d(response.body().toString(), "CharacterServiceRepository")


            return if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error: ${e.message}")
            return emptyList()
        }
    }
}