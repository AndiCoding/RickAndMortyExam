package com.example.rickandmortyexam.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropDown(
    selectedOption : String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    var expandedMenu by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(onClick = {
                expandedMenu = true
            }) {
                Text(selectedOption.ifEmpty { "Select options" })
            }
            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            expandedMenu = false
                            onOptionSelected(option)
                        },
                        text = { Text(option) }
                    )
                }
            }

        }
    }

}