package com.example.rickandmortyexam.screens.show_rick

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.ui.theme.Typography

@Composable
fun ShowRickScreen(showRickViewModel: ShowRickViewModel) {
    val characters = showRickViewModel.character.collectAsState() //observe the characters

    Column(Modifier.fillMaxSize())
    {
        Text("Show Rick Screen", style = Typography.headlineLarge)
        Button(onClick = {
            showRickViewModel.setCharacter()
        }) {
            Text("Get all characters")
        }
        LazyColumn {
            items(characters.value) { character ->
                Text(text = character.name)
            }
        }
    }
}