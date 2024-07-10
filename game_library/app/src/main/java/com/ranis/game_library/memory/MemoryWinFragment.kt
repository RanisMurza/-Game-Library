package com.ranis.game_library.memory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentMemoryWinBinding

class MemoryWinFragment : Fragment(R.layout.fragment_memory_win) {

    private var binding: FragmentMemoryWinBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMemoryWinBinding.bind(view)

        binding?.resetButton?.setOnClickListener {
            findNavController().navigate(R.id.action_memoryWinFragment3_to_memoryFragment)
        }
        binding?.exitButton?.setOnClickListener {
            findNavController().navigate(R.id.action_memoryWinFragment3_to_menuFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}