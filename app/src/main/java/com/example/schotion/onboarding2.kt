package com.example.schotion

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        val tv_lewati: TextView = findViewById(R.id.tv_skip)
        val tv_next: TextView = findViewById(R.id.tv_next)

        tv_lewati.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        tv_next.setOnClickListener{
            startActivity(Intent(this,onboarding3::class.java))
        }
        hideActionBar()
    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}