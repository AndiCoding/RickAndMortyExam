package com.example.rickandmortyexam.screens.show_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.components.CharacterItemDB
import com.example.rickandmortyexam.components.DropDown
import com.example.rickandmortyexam.ui.theme.Typography

@Composable
fun ShowRickScreen(showRickViewModel: ShowRickViewModel) {
    val characters = showRickViewModel.character.collectAsState() //observe the characters
    val options = listOf("Show all characters", "Show alive characters", "Show dead characters")

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

            DropDown(
                "Select an option",
                options,
                onOptionSelected =
                    { selectedOption ->
                        when (selectedOption) {
                            "Show all characters" -> showRickViewModel.getCharacter()
                            "Show alive characters" -> showRickViewModel.showAliveCharacters()
                            "Show dead characters" -> showRickViewModel.showDeadCharacters()
                        }
                    }
            )

                LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                items(characters.value) { character ->
                    CharacterItemDB(character)
                }
                }
    }
}
