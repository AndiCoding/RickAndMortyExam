package com.example.rickandmortyexam.screens.tictac_rick

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TicTacRickViewModel : ViewModel() {
    val board = mutableStateOf(List(3) { MutableList(3) { "" } })
    val currentPlayer = mutableStateOf("Rick")
    val winner = mutableStateOf<String?>(null)

    private val rickPositions = mutableListOf<Int>()
    private val mortyPositions = mutableListOf<Int>()

    private val winPossibilities = listOf(
        listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9),
        listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9),
        listOf(1, 5, 9), listOf(3, 5, 7)
    )

    fun makeMove(row: Int, col: Int) {
        if (board.value[row][col].isNotEmpty() || winner.value != null) return

        val newBoard = board.value.map { it.toMutableList() }
        newBoard[row][col] = currentPlayer.value
        board.value = newBoard

        val position = row * 3 + col + 1
        if (currentPlayer.value == "Rick") {
            rickPositions.add(position)
        } else {
            mortyPositions.add(position)
        }

        checkWinner()

        if (winner.value == null) {
            currentPlayer.value = if (currentPlayer.value == "Rick") "Morty" else "Rick"
        }
    }

    private fun checkWinner() {
        for (winCombination in winPossibilities) {
            if (winCombination.all { rickPositions.contains(it) }) {
                winner.value = "Rick"
                return
            }
            if (winCombination.all { mortyPositions.contains(it) }) {
                winner.value = "Morty"
                return
            }
        }

        if (rickPositions.size + mortyPositions.size == 9) {
            winner.value = "Draw"
        }
    }

    fun resetGame() {
        board.value = List(3) { MutableList(3) { "" } }
        currentPlayer.value = "Rick"
        winner.value = null
        rickPositions.clear()
        mortyPositions.clear()
    }
}
