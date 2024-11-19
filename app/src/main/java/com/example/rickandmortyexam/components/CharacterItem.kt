package com.example.rickandmortyexam.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortyexam.data.data_classes.RickAndMortyCharacter


@Composable
fun CharacterItem(character: RickAndMortyCharacter) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceEvenly
    ){
        Text(text = character.id.toString())
        Text(text = character.name ?: "Unknown")
    }


    }
