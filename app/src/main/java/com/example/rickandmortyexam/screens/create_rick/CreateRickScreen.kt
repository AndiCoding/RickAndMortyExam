package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.ui.theme.Typography

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {

    // Collect the created character from the view model
    val createdCharacterScreen by
        createRickViewModel.createdCharacter.collectAsState()

    
    Column(Modifier.fillMaxSize())
    {
        Text("Create Character", style = Typography.headlineLarge)
        TextField(
            value = createdCharacterScreen.name,
            onValueChange = { createRickViewModel.updateCharacterField("name", it) },
            label = { Text("Name") }
        )
        TextField(
            value = createdCharacterScreen.status,
            onValueChange = { createRickViewModel.updateCharacterField("status", it) },
            label = { Text("Status") }
        )
        TextField(
            value = createdCharacterScreen.species,
            onValueChange = {createRickViewModel.updateCharacterField("species", it) },
            label = { Text("Species") }
        )
        TextField(
            value = createdCharacterScreen.type,
            onValueChange = { createRickViewModel.updateCharacterField("type", it) },
            label = { Text("Type") }
        )
        TextField(
            value = createdCharacterScreen.gender,
            onValueChange = { createRickViewModel.updateCharacterField("gender", it) },
            label = { Text("Gender") }
        )

        Button(onClick = {
            createRickViewModel.insertCharacter()
        }){
            Text("Create Character")
        }

    }
}