package com.example.rickandmortyexam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.data.data_classes.RoomRMCharacter
import com.example.rickandmortyexam.ui.theme.Typography

// component that renders a single Character
// from the Room database

@Composable
fun CharacterItemDB(character: RoomRMCharacter) {
    val characterStatus = character.status
    val boxColor: Color = when (characterStatus.lowercase()){
        "alive" -> Color.Green
        "dead" -> Color.Red
        else -> Color.Gray
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
            Text(
                text = "Name: ${character.name}",
                style = Typography.headlineSmall
            )
            Text(
                text = "Status: ${character.status}",
                style = Typography.headlineSmall
            )
            Text(
                text = "Species: ${character.species}",
                style = Typography.headlineSmall
            )
        }

    }
}