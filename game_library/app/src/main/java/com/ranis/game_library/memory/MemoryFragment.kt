package com.ranis.game_library.memory

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentMemoryBinding
import com.ranis.game_library.databinding.IconCardBinding

class MemoryFragment : Fragment(R.layout.fragment_memory) {
    private var binding: FragmentMemoryBinding? = null
    private val buttons = ArrayList<MemoryGameButton>()

    private val flipAnimation = MemoryFlipAnimation()
    private var firstCard: MemoryGameButton? = null
    private var secondCard: MemoryGameButton? = null
    private var isBusy = false

    private val memoryData = MemoryData
    private val cardImages = memoryData.cardImages
    private var timer: CountDownTimer? = null
    private val timerDuration: Long = 60000
    private var pairsFound = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMemoryBinding.bind(view)

        initGame()
        binding!!.resetButton.setOnClickListener { initGame() }
        binding!!.reternButton.setOnClickListener{
            findNavController().navigate(R.id.action_memoryFragment_to_menuFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

    fun initGame(){
        cardImages.shuffle()
        binding!!.gridLayout.removeAllViews()
        buttons.clear()
        pairsFound = 0

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

        initTimer()

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
            pairsFound++

            if (pairsFound == cardImages.size / 2) {
                timer?.cancel()
                findNavController().navigate(R.id.action_memoryFragment_to_memoryWinFragment3)
            }
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

    private fun initTimer(){
        timer?.cancel()
        timer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding?.timerTextView?.text = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)
            }

            override fun onFinish() {
                binding?.timerTextView?.text = "00:00"
                findNavController().navigate(R.id.action_memoryFragment_to_memoryDefeatFragment2)
            }
        }.start()
    }
}