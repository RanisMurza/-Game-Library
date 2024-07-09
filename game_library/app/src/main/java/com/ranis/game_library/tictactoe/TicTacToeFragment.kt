package com.ranis.game_library.tictactoe

import android.graphics.Color
import android.graphics.fonts.Font
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentTicTacToeBinding
import com.ranis.game_library.tictactoe.Game.TicTacToe

class TicTacToeFragment : Fragment(R.layout.fragment_tic_tac_toe) {
    private var binding: FragmentTicTacToeBinding? = null

    private var currCell = Cell.X
    private var filledCells = 0

    private var tableButtons: List<Button?>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTicTacToeBinding.bind(view)

        val game = TicTacToe()

        binding?.run {

            tableButtons = listOf(
                button00, button01, button02,
                button10, button11, button12,
                button20, button21, button22
            )

            for (button in tableButtons as List<*>) {
                (button as Button).setOnClickListener {
                    onClickButton(game, button)
                }
            }

            buttonStart.setOnClickListener {

                buttonStart.text = "Restart"

                game.resetBoard()
                currCell = Cell.X
                filledCells = 0
                for (button in tableButtons as List<*>) { // enable the buttons and clear them
                    (button as Button).isEnabled = true
                    button.text = ""
                    button.typeface = resources.getFont(R.font.nunito_regular)
                    button.setTextColor(Color.BLACK)
                }
            }
        }

    }

    private fun disableTableButtons() {
        for (button in tableButtons as List<*>) {
            (button as Button).isEnabled = false
        }
    }

    private fun onClickButton(
        game: TicTacToe,
        button: Button,
    ) {
        button.isEnabled = false
        game.makeMove(getCoords(button), currCell)
        button.text = currCell.name.lowercase()

        filledCells++

        //check for a win
        val winner = game.checkWin(currCell)
        if (winner != null) {
            printWinner(winner)
            disableTableButtons()
        }

        //if the table is full
        if (filledCells == 9) {
            printTie()
            disableTableButtons()
        }
        //if stopButtonTriggered -> break

        currCell = if (currCell == Cell.X) {
            Cell.O
        } else {
            Cell.X
        }
    }

    private fun printTie() {
        //todo
    }

    private fun printWinner(winner: List<Pair<Int, Int>>) {
        fun getIndex(x: Int, y: Int) = x + y * 3

        for (coordinates in winner) {
            val button = tableButtons?.get(getIndex(coordinates.first, coordinates.second))
            button?.setTextColor(
                Color.GREEN)
            button?.typeface = resources.getFont(R.font.nunito_bold)
        }
    }

    private fun getCoords(button: Button): Array<Int> {
        val btnID = resources.getResourceName(button.id)
        return arrayOf(
            btnID[btnID.length - 1].toString().toInt(),
            btnID[btnID.length - 2].toString().toInt()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}






enum class Player {
    HUMAN, AI
}

enum class Cell {
    EMPTY, X, O
}