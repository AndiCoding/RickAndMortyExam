package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.rickandmortyexam.components.CharacterItem
import com.example.rickandmortyexam.data.data_classes.RickAndMortyCharacter

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {
    val characters = createRickViewModel.character.collectAsState()
    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }


    Column(Modifier.fillMaxSize())
    {
        Text("Create Rick&Morty Character")
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        TextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") }
        )
        TextField(
            value = species,
            onValueChange = { species = it },
            label = { Text("Species") }
        )
        TextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") }
        )
        TextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") }
        )
        Button(onClick = {
            val newCharacter = RickAndMortyCharacter(name = name, status = status, species = species, type = type, gender = gender)
        }){
            Text("Create Character")
        }

    }
}