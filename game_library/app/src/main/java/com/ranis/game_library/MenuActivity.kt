package com.ranis.game_library

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ranis.game_library.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private var binding: ActivityMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}