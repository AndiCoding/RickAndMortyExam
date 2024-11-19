package com.example.rickandmortyexam.screens.api_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.components.CharacterItem

@Composable
fun ApiRickScreen(apiRickViewModel: ApiRickViewModel) {

    LaunchedEffect(Unit) {
        apiRickViewModel.getInitialCharacters()
    }

    val characters = apiRickViewModel.characters.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Text("Api Rick Screen")
        LazyColumn {
            items(characters.value) { character ->
                CharacterItem(character)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { apiRickViewModel.prevCharacters() }
            ) {
                Text("Prev")
            }
            Button(
                onClick = {apiRickViewModel.nextCharacters()}
            ) {
                Text("Next")
            }
        }

    }
}
