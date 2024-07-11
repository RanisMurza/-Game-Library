package com.ranis.game_library.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var binding: FragmentMenuBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        binding?.run {
            buttonBlackjack.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_blackjackFragment)
            }
            buttonGallows.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_gallowsFragment)
            }
            buttonMemory.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_memoryFragment)
            }
            buttonTicTacToe.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_ticTacToeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}