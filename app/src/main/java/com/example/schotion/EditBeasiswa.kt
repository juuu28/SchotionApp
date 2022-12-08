package com.example.schotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditBeasiswa : AppCompatActivity() {
    //private lateinit var binding: ActivityAddBeasiswaBinding
    private lateinit var database: DatabaseReference
    private lateinit var nama: EditText
    private lateinit var penerima: EditText
    private lateinit var syarat: EditText
    private lateinit var periode: EditText
    private lateinit var catt: EditText
    private lateinit var batal: Button
    private lateinit var simpan: Button
    private lateinit var kalender: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_beasiswa)

        nama = findViewById(R.id.fill_nama)
        penerima = findViewById(R.id.fill_penerima)
        syarat = findViewById(R.id.fill_syarat)
        periode = findViewById(R.id.fillPeriode)
        catt = findViewById(R.id.fill_catt)
        batal = findViewById(R.id.btn_batal)
        simpan = findViewById(R.id.btn_simpan)

        hideActionBar()

        batal.setOnClickListener() {
            intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
        simpan.setOnClickListener {
            val namaBea = nama.text.toString().trim()
            val penerimaBea = penerima.text.toString().trim()
            val syaratBea = syarat.text.toString().trim()
            val periodeBea = periode.text.toString().trim()
            val cattBea = catt.text.toString().trim()

            if (namaBea.isEmpty()){
                nama.error = "Nama tidak boleh kosong"
            }
            if (penerimaBea.isEmpty()){
                penerima.error = "Penerima beasiswa tidak boleh kosong"
            }
            if (syaratBea.isEmpty()){
                syarat.error = "Syarat tidak boleh kosong"
            }
            if (periodeBea.isEmpty()){
                periode.error = "Periode tidak boleh kosong"
            }
            database = FirebaseDatabase.getInstance().getReference("Beasiswa")
            val beasiswas = Beasiswa(namaBea, penerimaBea, syaratBea, periodeBea, cattBea)
            database.child(namaBea).setValue(beasiswas).addOnCompleteListener {
                nama.text.clear()
                penerima.text.clear()
                syarat.text.clear()
                periode.text.clear()
                catt.text.clear()

                Toast.makeText(this, "Berhasil menambahkan beasiswa", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}