package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {
    val characters = createRickViewModel.character.collectAsState()

    var userCharacter by remember { mutableStateOf(RoomRMCharacter(
        name = "",
        status = "",
        species = "",
        type = "",
        gender = ""
    )) }


    Column(Modifier.fillMaxSize())
    {
        Text("Create Rick&Morty Character")
        TextField(
            value = userCharacter.name,
            onValueChange = { userCharacter = userCharacter.copy(name = it) },
            label = { Text("Name") }
        )
        TextField(
            value = userCharacter.status,
            onValueChange = { userCharacter = userCharacter.copy(status = it) },
            label = { Text("Status") }
        )
        TextField(
            value = userCharacter.species,
            onValueChange = { userCharacter = userCharacter.copy(species = it) },
            label = { Text("Species") }
        )
        TextField(
            value = userCharacter.type,
            onValueChange = { userCharacter = userCharacter.copy(type = it) },
            label = { Text("Type") }
        )
        TextField(
            value = userCharacter.gender,
            onValueChange = { userCharacter = userCharacter.copy(gender = it) },
            label = { Text("Gender") }
        )
        Button(onClick = {

            createRickViewModel.insertCharacter(userCharacter)
        }){
            Text("Create Character")
        }
        LazyColumn { items(characters.value) { character ->
            Text(text = character.name)
            Text(text = character.status)
            Text(text = character.species)
            Text(text = character.type)
            Text(text = character.gender)
        } }

    }
}