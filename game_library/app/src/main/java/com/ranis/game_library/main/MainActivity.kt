package com.ranis.game_library.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ranis.game_library.R
import com.ranis.game_library.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}