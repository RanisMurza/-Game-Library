package com.ranis.game_library.blackjack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentBlackjackBinding

class BlackjackFragment : Fragment(R.layout.fragment_blackjack) {
    private var binding: FragmentBlackjackBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlackjackBinding.bind(view)

        binding?.run {
            buttonPlay.setOnClickListener{
                findNavController().navigate(R.id.action_blackjackFragment_to_blackjackPlayFragment)
            }
            buttonRules.setOnClickListener{
                findNavController().navigate(R.id.action_blackjackFragment_to_blackjackRulesFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}