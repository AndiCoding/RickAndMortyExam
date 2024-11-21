package com.example.rickandmortyexam.screens.show_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.ui.theme.Typography

@Composable
fun ShowRickScreen(showRickViewModel: ShowRickViewModel) {
    val characters = showRickViewModel.character.collectAsState() //observe the characters

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
            Brush.verticalGradient(
                listOf(Color.Green, Color.Blue))
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    )
    {

        Text("Show Rick Screen", style = Typography.headlineLarge)
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Distribución horizontal
            verticalAlignment = Alignment.CenterVertically // Alineación vertical
        ) {
            Button(onClick = {
                showRickViewModel.getCharacter()
            }) {
                Text("Reload all characters")
            }
            Button(onClick = {
                showRickViewModel.showAliveCharacters()
            }) {
                Text("Show alive")
            }
            Button(onClick = {
                showRickViewModel.showDeadCharacters()
            }) {
                Text("Show dead")
            }


        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(characters.value) { character ->
                val boxColor = if (character.status.equals("Alive", ignoreCase = true)) {
                    Color.Green
                } else {
                    Color.Red
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, Color.LightGray)
                        .background(boxColor)


                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Name: ${character.name}", style = Typography.headlineSmall)
                        Text(text = "Status: ${character.status}", style = Typography.headlineSmall)
                        Text(
                            text = "Species: ${character.species}",
                            style = Typography.headlineSmall
                        )
                        Text(text = "Type: ${character.type}", style = Typography.headlineSmall)
                        Text(text = "Gender: ${character.gender}", style = Typography.headlineSmall)
                    }

                }
            }
        }
    }
}