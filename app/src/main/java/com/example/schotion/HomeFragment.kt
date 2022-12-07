package com.example.schotion

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schotion.databinding.FragmentHomeBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.edit_text_layout.*
import kotlinx.android.synthetic.main.elemen_beasiswa.*
import kotlinx.android.synthetic.main.elemen_beasiswa.view.*


class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val bindingHome get() = _binding!!
    private lateinit var dbref : DatabaseReference
    private lateinit var BeasiswaRecyclerview : RecyclerView
    private lateinit var BeasiswaArrayList : ArrayList<Beasiswa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingHome.addButton.setOnClickListener{
            val intent = Intent (this@HomeFragment.requireContext(), AddBeasiswa::class.java)
            startActivity(intent)
        }

        BeasiswaRecyclerview = bindingHome.rvBeasiswa
        BeasiswaRecyclerview.layoutManager = LinearLayoutManager(activity)
        BeasiswaRecyclerview.setHasFixedSize(false)

        BeasiswaArrayList = arrayListOf<Beasiswa>()
        getBeasiswaData()

        return bindingHome.root
    }
    private fun getBeasiswaData() {

        BeasiswaRecyclerview.visibility = View.GONE
        dbref = FirebaseDatabase.getInstance().getReference("Beasiswa")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                BeasiswaArrayList.clear()
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val beasiswa = userSnapshot.getValue(Beasiswa::class.java)
                        BeasiswaArrayList.add(beasiswa!!)
                    }
                    val adapter = BeasiswaAdapter(BeasiswaArrayList)
                    BeasiswaRecyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : BeasiswaAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@HomeFragment.requireContext(), BeasiswaDetail::class.java)
                            intent.putExtra("namaBeasiswa", BeasiswaArrayList[position].namaBeasiswa)
                            intent.putExtra("penerimaBeasiswa", BeasiswaArrayList[position].penerimaBeasiswa)
                            intent.putExtra("syaratBeasiswa", BeasiswaArrayList[position].syaratBeasiswa)
                            intent.putExtra("periodePendaftaran", BeasiswaArrayList[position].periodePendaftaran)
                            intent.putExtra("cattTambahan", BeasiswaArrayList[position].cattTambahan)
                            startActivity(intent)
                        }

                    })
                    BeasiswaRecyclerview.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}