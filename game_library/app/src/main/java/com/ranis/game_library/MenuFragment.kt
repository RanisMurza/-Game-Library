package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var binding: FragmentMenuBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}