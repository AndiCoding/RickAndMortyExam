package com.example.rickandmortyexam.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter


@Composable
fun CharacterItem(character: ApiRMCharacter) {
    Box(modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Id: " + character.id.toString())
            Text(text = "Name: " + character.name ?: "Unknown")
            AsyncImage(
                model = character.image,
                contentDescription = character.name ?: "Unknown"
            )
        }
    }
}
