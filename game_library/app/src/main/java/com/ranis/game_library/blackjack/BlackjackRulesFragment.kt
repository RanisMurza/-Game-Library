package com.ranis.game_library.blackjack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentRulesBlackjackBinding

class BlackjackRulesFragment : Fragment(R.layout.fragment_rules_blackjack) {
    private var binding: FragmentRulesBlackjackBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRulesBlackjackBinding.bind(view)

        binding?.run {
            buttonOK.setOnClickListener{
                findNavController().navigate(R.id.action_blackjackRulesFragment_to_blackjackFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}