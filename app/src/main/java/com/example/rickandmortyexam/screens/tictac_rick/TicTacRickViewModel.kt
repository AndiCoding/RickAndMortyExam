package com.example.rickandmortyexam.screens.tictac_rick

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TicTacRickViewModel : ViewModel() {
    val board = mutableStateOf(List(3) { MutableList(3) { "" } })
    val currentPlayer = mutableStateOf("Rick")

    fun makeMove(row: Int, col: Int) {
        if (board.value[row][col].isEmpty()) {
            board.value[row][col] = currentPlayer.value
            currentPlayer.value = if (currentPlayer.value == "Rick") "Morty" else "Rick"
        }
    }

    fun resetGame() {
        board.value = List(3) { MutableList(3) { "" } }
        currentPlayer.value = "Rick"
    }
}