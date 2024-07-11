package com.ranis.game_library.gallows

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentGallowsBinding
import kotlin.random.Random

class GallowsFragment : Fragment(R.layout.fragment_gallows) {
    private var binding: FragmentGallowsBinding? = null

    private var falseCount = 0
    private var gameOverFlag = true
    private lateinit var word: String
    private lateinit var targetWord: String
    private lateinit var indexes: MutableList<Int>
    private var randomNumber = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGallowsBinding.bind(view)
        startGame()
        for (letter in 'a'..'z') {
            val buttonId = resources.getIdentifier(letter.toString(), "id", requireContext().packageName)
            val button = view.findViewById<View>(buttonId)
            button?.setOnClickListener {
                indexes = findIndexes(binding, word, letter)
                targetWord = displayLetters(indexes, targetWord, letter)
                button.visibility = View.GONE
            }
        }
    }

    private fun startGame() {
        Log.d("GallowsFragment", "Starting new game")
        callBackButtons()
        falseCount = 0
        gameOverFlag = true  // Reset the gameOverFlag at the start of the game
        binding?.gallows?.setImageResource(0)
        randomNumber = Random.nextInt(0, 320)
        word = GallowsWords.DICTIONARY[randomNumber]
        createBlanks(word.length, binding)
        targetWord = binding?.word?.text.toString()
        Log.d("GallowsFragment", "gameOverFlag reset to true")
    }

    private fun callBackButtons() {
        binding?.let {
            for (letter in 'a'..'z') {
                val buttonId = resources.getIdentifier(letter.toString(), "id", requireContext().packageName)
                val button = it.root.findViewById<View>(buttonId)
                button?.visibility = View.VISIBLE
            }
        }
    }

    private fun createBlanks(size: Int, binding: FragmentGallowsBinding?) {
        binding?.word?.text = "_ ".repeat(size)
    }

    private fun findIndexes(binding: FragmentGallowsBinding?, word: String, letter: Char): MutableList<Int> {
        val indexes = mutableListOf<Int>()

        word.mapIndexed { index, char ->
            if (char == letter) {
                indexes.add(index)
            }
        }
        if (indexes.isEmpty()) {
            falseCount++
            updateImage(binding, falseCount)
            if (falseCount >= 10) {
                gameOverFlag = false
                Log.d("GallowsFragment", "Game over: falseCount reached 10")
                showGameOverDialog(gameOverFlag)
            }
        }

        return indexes
    }

    private fun updateImage(binding: FragmentGallowsBinding?, falseCount: Int) {
        val imageName = "hangman_$falseCount"
        val imageResourceId = resources.getIdentifier(imageName, "drawable", requireContext().packageName)
        binding?.gallows?.setImageResource(imageResourceId)
    }

    private fun displayLetters(indexes: MutableList<Int>, targetWord: String, letter: Char): String {
        val stringBuilder = StringBuilder(targetWord)
        if (indexes.isNotEmpty()) {
            indexes.map { index ->
                stringBuilder.setCharAt(index * 2, letter.uppercaseChar())
            }
            binding?.word?.text = stringBuilder.toString()
        }

        if (!stringBuilder.contains("_")) {
            gameOverFlag = true
            Log.d("GallowsFragment", "Game won: All letters found")
            showGameOverDialog(gameOverFlag)
        }

        return stringBuilder.toString()
    }

    private fun showGameOverDialog(gameOverFlag: Boolean) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)

        if (gameOverFlag) {
            builder.setTitle("YOU WON")
            builder.setMessage("Congrats! You Won The Game")

            builder.setPositiveButton("Play Again") { _, _ ->
                startGame()
            }
            builder.setNegativeButton("Exit") { _, _ ->
                System.exit(0)
            }

        } else {
            builder.setTitle("GAME OVER")
            builder.setMessage("You Lost The Game. The word was ${word.uppercase()}")

            builder.setPositiveButton("Play Again") { _, _ ->
                startGame()
            }
            builder.setNegativeButton("Exit") { _, _ ->
                System.exit(0)
            }

        }

        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
