package com.example.rickandmortyexam.screens.tictac_rick

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortyexam.R
import com.example.rickandmortyexam.ui.theme.Typography

@Composable
fun TicTacRickScreen(viewModel: TicTacRickViewModel) {
    val board by viewModel.board
    val currentPlayer by viewModel.currentPlayer

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Tic-Tac Rick", style = Typography.headlineLarge)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Current Player: $currentPlayer", style = Typography.bodyLarge)
            for (row in 0 until 3) {
                Row {
                    for (col in 0 until 3) {
                        val player = board[row][col]
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .border(1.dp, Color.Black)
                                .clickable { viewModel.makeMove(row, col) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (player == "Rick") {
                                Image(painter = painterResource(id = R.drawable.rick_face), contentDescription = "Rick")
                            } else if (player == "Morty") {
                                Image(painter = painterResource(id = R.drawable.morty_face), contentDescription = "Morty")
                            }
                        }
                    }
                }
            }
            Button(onClick = { viewModel.resetGame() }) {
                Text("Reset Game")
            }

        }
        Spacer(modifier = Modifier.size(64.dp))





    }
}