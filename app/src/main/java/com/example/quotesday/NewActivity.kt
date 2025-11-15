package com.example.quotesday

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesday.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private val binding: ActivityNewBinding by lazy {
        ActivityNewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val fragment = MainFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)

            .commit()
    }
}
