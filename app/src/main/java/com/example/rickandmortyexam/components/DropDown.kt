package com.example.rickandmortyexam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// Reusable Dropdown component. It takes a list of options
// and a callback function to handle the selected option.
// The selected option is displayed in the dropdown.

@Composable
fun DropDown(
    selectedOption : String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .clickable { expandedMenu = !expandedMenu }
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(

            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(selectedOption.ifEmpty { "Select options" })
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")

        }
        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier.fillMaxWidth(),
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