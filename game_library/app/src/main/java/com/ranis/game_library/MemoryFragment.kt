package com.ranis.game_library

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentMemoryBinding
import com.ranis.game_library.databinding.IconCardBinding

class MemoryFragment : Fragment(R.layout.fragment_memory) {
    private var binding: FragmentMemoryBinding? = null
    private val buttons = ArrayList<MemoryGameButton>()
    private val cardImages = mutableListOf(
        R.drawable.corn_cat, R.drawable.corn_cat

    )
    private val flipAnimation = MemoryFlipAnimation()
    private var firstCard: MemoryGameButton? = null
    private var secondCard: MemoryGameButton? = null
    private var isBusy = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMemoryBinding.bind(view)

        binding!!.resetButton.setOnClickListener { initGame() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

    fun initGame(){
        cardImages.shuffle()
        binding!!.gridLayout.removeAllViews()
        buttons.clear()

        for (i in cardImages.indices) {
            val cardBinding = IconCardBinding.inflate(LayoutInflater.from(requireContext()), binding!!.gridLayout, false)
            val button = cardBinding.iconCard
            button.tag = cardImages[i]

            val memoryButton = MemoryGameButton(button, false)
            button.setOnClickListener {
                onCardClicked(memoryButton, i)
            }

            buttons.add(memoryButton)
            binding!!.gridLayout.addView(cardBinding.root)
        }

    }


    private fun onCardClicked(but: MemoryGameButton, index: Int) {
        var button = but.imageButton
        if (isBusy || button == firstCard?.imageButton  || button == secondCard?.imageButton || but.isBusyTag) return

        if (firstCard == null) {
            firstCard = but
            flipAnimation.flipCard(button, cardImages[index])
        } else if (secondCard == null) {
            secondCard = but
            flipAnimation.flipCard(button, cardImages[index])

            Handler().postDelayed({
                checkForMatch()
            }, 500)
        }
    }

    private fun checkForMatch() {
        val firstButton = firstCard?.imageButton
        val secondButton = secondCard?.imageButton
        isBusy = true
        if (firstButton?.tag == secondButton?.tag) {
            firstCard?.isBusyTag = true
            secondCard?.isBusyTag = true
            firstCard = null
            secondCard = null
            isBusy = false
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                firstButton?.let { flipAnimation.flipCardBack(it) }
                secondButton?.let { flipAnimation.flipCardBack(it) }
                firstCard = null
                secondCard = null
                isBusy = false
            }, 500)
        }
    }
}