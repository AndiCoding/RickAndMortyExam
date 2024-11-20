package com.example.rickandmortyexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.rickandmortyexam.data.room.CharacterDatabaseRepository
import com.example.rickandmortyexam.screens.api_rick.ApiRickViewModel
import com.example.rickandmortyexam.navigation.AppNavigation
import com.example.rickandmortyexam.screens.create_rick.CreateRickViewModel
import com.example.rickandmortyexam.screens.show_rick.ShowRickViewModel
import com.example.rickandmortyexam.ui.theme.RickAndMortyExamTheme

class MainActivity : ComponentActivity() {
    private val apiRickViewModel by viewModels<ApiRickViewModel>()
    private val createRickViewModel by viewModels<CreateRickViewModel>()
    private val showRickViewModel by viewModels<ShowRickViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterDatabaseRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            RickAndMortyExamTheme {
                AppNavigation(apiRickViewModel,createRickViewModel,showRickViewModel)
            }
            }
        }
    }
