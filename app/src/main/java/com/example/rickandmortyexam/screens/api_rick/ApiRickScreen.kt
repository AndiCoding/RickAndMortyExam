package com.example.rickandmortyexam.screens.api_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.components.CharacterItem
import com.example.rickandmortyexam.components.PrevNextButtons
import com.example.rickandmortyexam.ui.theme.Typography



@Composable
fun ApiRickScreen(apiRickViewModel: ApiRickViewModel) {

    val characters = apiRickViewModel.characters.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Green, Color.Blue))
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Api Rick Screen", style = Typography.headlineLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(characters.value) { character ->
                CharacterItem(character)
            }
        }


        Column(
            modifier = Modifier.background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = apiRickViewModel.nameText.collectAsState().value,
                onValueChange = { apiRickViewModel.setNameText(it) },
                label = { Text("Character Name") }
            )
            TextField(
                value = apiRickViewModel.statusText.collectAsState().value,
                onValueChange = { apiRickViewModel.setStatusText(it)},
                label = { Text("Character Status") }
            )
            Text(apiRickViewModel.searchResult.collectAsState().value)

            Row {
                Button(onClick = { apiRickViewModel.searchCharacters() }) {
                    Text("Search")
                }
                Spacer(modifier = Modifier.width(24.dp))
                Button(onClick = { apiRickViewModel.resetCharacters() }) {
                    Text("Reset")
                }

            }

            PrevNextButtons(
                apiRickViewModel::prevCharacters,
                apiRickViewModel::nextCharacters,
                apiRickViewModel.pageNumber.collectAsState().value
            )
        }

    }
}
