package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var binding: FragmentMenuBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        binding?.run {
            button1.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_blackjackFragment)
            }
            button2.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_gallowsFragment)
            }
            button3.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_memoryFragment)
            }
            button4.setOnClickListener{
                findNavController().navigate(R.id.action_menuFragment_to_ticTacToeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}