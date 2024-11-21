package com.example.rickandmortyexam.screens.show_rick

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.ui.theme.Typography

@Composable
fun ShowRickScreen(showRickViewModel: ShowRickViewModel) {
    val characters = showRickViewModel.character.collectAsState() //observe the characters
    var expandedMenu by remember { mutableStateOf(false) }
    val options = listOf("Reload all characters", "Show alive characters", "Show dead characters")
    var selectedOption by remember { mutableStateOf("Select an option") }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    )
    {
        Text("Show Rick Screen", style = Typography.headlineLarge)


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { expandedMenu = !expandedMenu }
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(selectedOption, style = Typography.headlineSmall)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                }

                DropdownMenu(
                    expanded = expandedMenu,
                    onDismissRequest = { expandedMenu = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                expandedMenu = false
                                selectedOption = option
                                when (option) {
                                    "Reload all characters" -> showRickViewModel.getCharacter()
                                    "Show alive characters" -> showRickViewModel.showAliveCharacters()
                                    "Show dead characters" -> showRickViewModel.showDeadCharacters()
                                }
                            },
                            text = { Text(option) }
                        )
                    }
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
                            Text(
                                text = "Status: ${character.status}",
                                style = Typography.headlineSmall
                            )
                            Text(
                                text = "Species: ${character.species}",
                                style = Typography.headlineSmall
                            )
                            Text(text = "Type: ${character.type}", style = Typography.headlineSmall)
                            Text(
                                text = "Gender: ${character.gender}",
                                style = Typography.headlineSmall
                            )
                        }

                    }
                }
            }
        }
    }

