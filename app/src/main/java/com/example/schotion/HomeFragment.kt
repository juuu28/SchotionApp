package com.example.schotion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schotion.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val bindingHome get() = _binding!!
    private lateinit var dbref : DatabaseReference
    private lateinit var BeasiswaRecyclerview : RecyclerView
    private lateinit var BeasiswaArrayList : ArrayList<Beasiswa>

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
        BeasiswaRecyclerview = bindingHome.rvBeasiswa
        BeasiswaRecyclerview.layoutManager = LinearLayoutManager(activity)
        BeasiswaRecyclerview.setHasFixedSize(true)

        BeasiswaArrayList = arrayListOf<Beasiswa>()
        getBeasiswaData()
    }
    private fun getBeasiswaData() {

        dbref = FirebaseDatabase.getInstance().getReference("Beasiswa")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val beasiswa = userSnapshot.getValue(Beasiswa::class.java)
                        BeasiswaArrayList.add(beasiswa!!)

                    }

                    BeasiswaRecyclerview.adapter = BeasiswaAdapter(BeasiswaArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}