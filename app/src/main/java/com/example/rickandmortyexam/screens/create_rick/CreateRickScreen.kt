package com.example.rickandmortyexam.screens.create_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.ui.theme.Typography
import kotlinx.coroutines.delay

//Screen 3
@Composable
fun CreateRickScreen(createRickViewModel: CreateRickViewModel) {

    // Collect updated character fields, that has been updated by the user
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Green, Color.Blue))
            ),
        Arrangement.SpaceBetween,
        horizontalAlignment =
            Alignment.CenterHorizontally
    )
    {
        Text("Create Character", style = Typography.headlineLarge)
        Column {
            TextField(
                value = createdCharacterScreen.name,
                onValueChange = { createRickViewModel.updateCharacterField("name", it) },
                label = { Text("Name") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = createdCharacterScreen.status,
                onValueChange = { createRickViewModel.updateCharacterField("status", it) },
                label = { Text("Status") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = createdCharacterScreen.species,
                onValueChange = {createRickViewModel.updateCharacterField("species", it) },
                label = { Text("Species") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = createdCharacterScreen.type,
                onValueChange = { createRickViewModel.updateCharacterField("type", it) },
                label = { Text("Type") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = createdCharacterScreen.gender,
                onValueChange = { createRickViewModel.updateCharacterField("gender", it) },
                label = { Text("Gender") }
            )

        }

        Button(onClick = {
            createRickViewModel.insertCharacter()
        }){
            Text("Create Character")
        }


    }
    // displays a message if the character was created successfully or not
    message?.let {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(if (it.contains("successfully")) Color(0xFF4BB543) else Color(0xFFFF3333))
                .padding(16.dp)
        ) {
            Text(it, color = Color.White)
        }
    }
}