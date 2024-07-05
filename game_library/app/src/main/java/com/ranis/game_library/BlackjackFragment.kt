package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentBlackjackBinding

class BlackjackFragment : Fragment(R.layout.fragment_blackjack) {
    private var binding: FragmentBlackjackBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlackjackBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}