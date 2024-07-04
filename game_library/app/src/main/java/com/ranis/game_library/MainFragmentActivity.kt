package com.ranis.game_library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ranis.game_library.databinding.ActivityFragmentMainBinding

class MainFragmentActivity : AppCompatActivity() {

    private var binding: ActivityFragmentMainBinding? = null
    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)
        binding = ActivityFragmentMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}