package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentMemoryBinding

class MemoryFragment : Fragment(R.layout.fragment_memory) {
    private var binding: FragmentMemoryBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMemoryBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}