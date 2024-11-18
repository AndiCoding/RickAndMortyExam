package com.example.rickandmortyexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.rickandmortyexam.screens.api_rick.ApiRickViewModel
import com.example.rickandmortyexam.navigation.AppNavigation
import com.example.rickandmortyexam.ui.theme.RickAndMortyExamTheme

class MainActivity : ComponentActivity() {
    val apiRickViewModel : ApiRickViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyExamTheme {
                AppNavigation(apiRickViewModel)
            }
            }
        }
    }


