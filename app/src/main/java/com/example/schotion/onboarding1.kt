package com.example.schotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class onboarding1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
        hideActionBar()

        val tv:TextView=findViewById(R.id.tv_OnNext)
        val tv1:TextView=findViewById(R.id.tv_OnSkip)

        tv.setOnClickListener{
            startActivity(Intent(this,onboarding2::class.java))
        }

        tv1.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private fun hideActionBar() {
        supportActionBar?.hide()
        }
}