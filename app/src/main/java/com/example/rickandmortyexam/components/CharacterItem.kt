package com.example.rickandmortyexam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyexam.data.data_classes.ApiRMCharacter

// component that renders a single Character
// from the API

@Composable
fun CharacterItem(character: ApiRMCharacter) {

    val statusColor = character.status.toString().let {
        when(it){
            "Alive" -> Color.Green
            "Dead" -> Color.Red
            else -> Color.Gray
        }
    }

    val speciesColor = character.species.toString().let {
        when(it){
            "Human" -> Color(0xFFFCC266)
            "Alien" -> Color(0xFF2AFF4D)
            else -> Color.Gray
        }
    }



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
                    modifier = Modifier
                        .weight(1f),
                    model = character.image,
                    contentDescription = character.name ?: "Unknown"
                )
                Box(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .padding(8.dp)
                        .weight(1f)
                        .fillMaxHeight()
                ){
                   Column {
                       Text(
                           character.name.toString(),
                           fontSize = 16.sp,

                       )
                       Row(
                           Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           Text(
                               character.status.toString(),
                               fontSize = 16.sp,
                               modifier = Modifier.background(statusColor)
                           )
                            Text(
                                 character.species.toString(),
                                 fontSize = 16.sp,
                                modifier = Modifier.background(speciesColor)
                            )
                       }
                   }
                }

            }
        }
    }
}
