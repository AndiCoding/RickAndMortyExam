package com.example.rickandmortyexam.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter



@Composable
fun CharacterItem(character: ApiRMCharacter) {
    Box(modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black)) {
        Column {
            Text(text = character.id.toString())
            Text(text = character.name ?: "Unknown")
            AsyncImage(
                model = character.image,
                contentDescription = character.name ?: "Unknown"
            )
        }
    }




    }
