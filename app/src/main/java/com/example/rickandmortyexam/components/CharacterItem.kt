package com.example.rickandmortyexam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter

// component that renders a single Character
// from the API

@Composable
fun CharacterItem(character: ApiRMCharacter) {
    Box(modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black), contentAlignment = Alignment.Center) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = character.name ?: "Unknown"
                )
                Text(
                    character.name.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .background(Color.Cyan)
                        .padding(8.dp)

                )
            }
        }
    }
}
