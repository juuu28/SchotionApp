package com.example.schotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class onboarding3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)

        val tv_start: TextView =findViewById(R.id.tv_getStarted)

        tv_start.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        hideActionBar()
    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}