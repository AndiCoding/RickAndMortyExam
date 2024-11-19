package com.example.rickandmortyexam.data.services

import android.util.Log
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.SQLException

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


    suspend fun getCharacters(url: String = "character"): Pair<List<ApiRMCharacter>, Info?> {

        val defaultResponse = Pair(emptyList<ApiRMCharacter>(), null)
        try {
            val response = _characterService.getCharacters(url)

            if (response.isSuccessful) {
                return Pair(response.body()?.results ?: emptyList(), response.body()?.info)
            }
        } catch (e: SQLException) {
            Log.e("CharacterRepository SQL", "Error: ${e.message}")

        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error: ${e.message}")
        }
        return defaultResponse

    }



}



/*
* suspend fun getCharacters(): List<ApiRMCharacter> {
        val characters = mutableListOf<ApiRMCharacter>()

        try {
            val response = _characterService.getCharacters()

            if (response.isSuccessful) {
                characters.addAll(response.body()?.results ?: emptyList())
                var info = response.body()?.info

                /*
                var nextUrl = response.body()?.info?.next
                while (nextUrl != null){
                    _characterService.getCharacters(nextUrl).let {

                        if (it.isSuccessful) {
                            characters.addAll(it.body()?.results ?: emptyList())
                            nextUrl = it.body()?.info?.next

                        } else {
                            nextUrl = null
                        }
                    }
                }

                 */
            }
        } catch (e: SQLException) {
            Log.e("CharacterRepository SQL", "Error: ${e.message}")
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error: ${e.message}")
        }

        return characters
    }
* */