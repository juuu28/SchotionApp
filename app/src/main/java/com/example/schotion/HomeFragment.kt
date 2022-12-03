package com.example.schotion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schotion.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val bindingHome get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingHome.addButton.setOnClickListener{
            val intent = Intent (this@HomeFragment.requireContext(), AddBeasiswa::class.java)
            startActivity(intent)
        }
        return bindingHome.root
    }
}