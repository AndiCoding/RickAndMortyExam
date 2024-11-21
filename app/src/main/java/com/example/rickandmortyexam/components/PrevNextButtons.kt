package com.example.rickandmortyexam.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape


// Reusable component that renders two buttons
// for navigating to the previous and next pages.
@Composable
fun PrevNextButtons(
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    pageNumber: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.SpaceEvenly,
        Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(1f),
            shape = RectangleShape,
            onClick = onPrevClick
        ) {
            Text("Prev")
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Text(pageNumber)
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            modifier = Modifier.weight(1f),
            shape = RectangleShape,
            onClick = onNextClick
        ) {
            Text("Next")
        }
    }
}