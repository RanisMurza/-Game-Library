package com.ranis.game_library

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ranis.game_library.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val button = findViewById<Button>(R.id.buttonMain)
        button.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}