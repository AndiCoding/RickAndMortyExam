package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.rickandmortyexam.ui.theme.Typography

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {

    var userCharacter by remember { mutableStateOf(RoomRMCharacter(
        name = "",
        status = "",
        species = "",
        type = "",
        gender = ""
    )) }

    // Collect the created character from the view model
    val createdCharacter by
        createRickViewModel
            .getCreatedCharacter()
            .collectAsState(RoomRMCharacter())

    Column(Modifier.fillMaxSize())
    {
        Text("Create Character", style = Typography.headlineLarge)
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


        // displays the created character if it exists
        if (createdCharacter.id != 0) {
            Text("Created Character: ${createdCharacter.name}")
        }


    }
}