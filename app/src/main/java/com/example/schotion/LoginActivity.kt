package com.example.schotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.backToHome).setOnClickListener(){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        hideActionBar()
        val goRegist = findViewById<TextView>(R.id.tv_goToRegister)

        goRegist.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}