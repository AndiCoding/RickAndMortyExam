package com.example.rickandmortyexam.screens.api_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.components.CharacterItem
import com.example.rickandmortyexam.components.PrevNextButtons
import com.example.rickandmortyexam.ui.theme.Typography


@Composable
fun ApiRickScreen(apiRickViewModel: ApiRickViewModel) {
    val characters = apiRickViewModel.characters.collectAsState()
    val nameField = apiRickViewModel.nameText.collectAsState()
    val statusField = apiRickViewModel.statusText.collectAsState()


    Column(Modifier.fillMaxSize()) {
        Text("Api Rick Screen", style = Typography.headlineLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(characters.value) { character ->
                CharacterItem(character)
            }
        }


        // converting stateflow into a state that can be used in the textfield
        // updating the stateflow in viewmodel when the textfield value changes
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
        Row {
            Button(onClick = { apiRickViewModel.searchCharacters(nameField.value, statusField.value) }) {
                Text("Search")
            }

            // displays the result text of the search
            Text(apiRickViewModel.searchResult.collectAsState().value)

        }



        PrevNextButtons(apiRickViewModel::prevCharacters, apiRickViewModel::nextCharacters)
    }
}
