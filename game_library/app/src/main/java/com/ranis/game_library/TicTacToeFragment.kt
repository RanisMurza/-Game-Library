package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentTicTacToeBinding

class TicTacToeFragment : Fragment(R.layout.fragment_tic_tac_toe) {
    private var binding: FragmentTicTacToeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTicTacToeBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}