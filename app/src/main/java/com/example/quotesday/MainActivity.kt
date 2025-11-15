package com.example.quotesday

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesday.databinding.ActivityMainBinding

//ctrl + alt + l -> reformat
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.inspireBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
