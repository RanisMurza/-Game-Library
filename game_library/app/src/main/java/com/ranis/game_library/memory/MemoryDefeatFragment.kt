package com.ranis.game_library.memory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentMemoryDefeatBinding

class MemoryDefeatFragment : Fragment(R.layout.fragment_memory_defeat) {
    private var binding: FragmentMemoryDefeatBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMemoryDefeatBinding.bind(view)

        binding?.resetButton?.setOnClickListener {
            findNavController().navigate(R.id.action_memoryDefeatFragment2_to_memoryFragment)
        }
        binding?.exitButton?.setOnClickListener {
            findNavController().navigate(R.id.action_memoryDefeatFragment2_to_menuFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}