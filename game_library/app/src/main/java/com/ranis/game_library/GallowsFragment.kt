package com.ranis.game_library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranis.game_library.databinding.FragmentGallowsBinding

class GallowsFragment : Fragment(R.layout.fragment_gallows) {
    private var binding: FragmentGallowsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGallowsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}