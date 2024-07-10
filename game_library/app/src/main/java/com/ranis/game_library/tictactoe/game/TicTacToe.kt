package com.ranis.game_library.tictactoe.game

import com.ranis.game_library.tictactoe.Cell

class TicTacToe {
    private val board = Array(3) { Array(3) { Cell.EMPTY } }

    fun checkWin(player: Cell): List<Pair<Int, Int>>? {
        val winPositions = listOf(
            listOf(0 to 0, 0 to 1, 0 to 2),
            listOf(1 to 0, 1 to 1, 1 to 2),
            listOf(2 to 0, 2 to 1, 2 to 2),
            listOf(0 to 0, 1 to 0, 2 to 0),
            listOf(0 to 1, 1 to 1, 2 to 1),
            listOf(0 to 2, 1 to 2, 2 to 2),
            listOf(0 to 0, 1 to 1, 2 to 2),
            listOf(0 to 2, 1 to 1, 2 to 0)
        )

        for (position in winPositions) {
            if (position.all { (x, y) -> board[x][y] == player }) {
                return position
            }
        }

        return null
    }

    fun makeMove(coords: Array<Int>, player: Cell): Boolean {
        return if (board[coords[0]][coords[1]] == Cell.EMPTY) {
            board[coords[0]][coords[1]] = player
            true
        } else {
            false
        }
    }

    fun resetBoard() {
        for (row in board) {
            for (i in 0..2) {
                row[i] = Cell.EMPTY
            }
        }
    }
}