package com.example.rickandmortyexam.data.services

import android.util.Log
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.Info
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.HttpException
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


    // Function to get characters from the API, and handles errors.
    // Returns a pair of characters and metadata about the current page.

    suspend fun getCharacters(url: String): Pair<List<ApiRMCharacter>, Info?> {
        val defaultResponse = Pair(emptyList<ApiRMCharacter>(), null)
        try {
            val response = _characterService.getCharacters(url)

            if (response.isSuccessful) {
                return Pair(response.body()?.results ?: emptyList(), response.body()?.info)
            } else {
                Log.e("CharacterRepository", "API Error: ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            Log.e("CharacterRepository", "Network Error: ${e.message}")
        } catch (e: HttpException) {
            Log.e("CharacterRepository", "HTTP Error Code: ${e.code()}, HTTP Error: ${e.message}")
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Unknown Error: ${e.message}")
        }


        return defaultResponse
    }
}