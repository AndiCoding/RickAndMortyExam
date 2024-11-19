package com.example.rickandmortyexam.screens.api_rick

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import com.example.rickandmortyexam.components.CharacterItem
import com.example.rickandmortyexam.components.PrevNextButtons
import com.example.rickandmortyexam.ui.theme.Typography


@Composable
fun ApiRickScreen(apiRickViewModel: ApiRickViewModel) {

    LaunchedEffect(Unit) {
        apiRickViewModel.getInitialCharacters()
    }

    val characters = apiRickViewModel.characters.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Text("Api Rick Screen", style = Typography.headlineLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(characters.value) { character ->
                CharacterItem(character)
            }
        }

        PrevNextButtons(apiRickViewModel::prevCharacters, apiRickViewModel::nextCharacters)
    }
}
