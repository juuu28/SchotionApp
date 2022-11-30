package com.example.schotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.schotion.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_beasiswa.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val Home = HomeFragment()
    val Beasiswa = BeasiswaFragment()
    val Profile = ProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.toLogin).setOnClickListener(){
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        hideActionBar()

        binding.BottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main, Home)
                    commit()
                }
                R.id.beaNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main, Beasiswa)
                    commit()
                }
                R.id.profileNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main, Profile)
                    commit()
                }
                else ->{

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}