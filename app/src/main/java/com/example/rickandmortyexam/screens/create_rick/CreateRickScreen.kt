package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.ui.theme.Typography
import kotlinx.coroutines.delay

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {

    // Collect the created character from the view model
    val createdCharacterScreen by
        createRickViewModel.createdCharacter.collectAsState()

    val message by createRickViewModel.message.collectAsState()

    // shows success or error message for 3 seconds
    LaunchedEffect(message) {
        if (message != null) {
            delay(3000)
            createRickViewModel.clearMessage()
        }
    }

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


        // displays a message if the character was created successfully or not
        message?.let {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
                    .background(if (it.contains("successfully")) Color(0xFF4BB543) else Color(0xFFFF3333))
                    .padding(16.dp)
            ) {
                Text(it, color = Color.White)
            }
        }

    }
}