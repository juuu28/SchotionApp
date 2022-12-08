package com.example.schotion

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_beasiswa_detail.*

class BeasiswaDetail : AppCompatActivity() {

    private lateinit var nama: TextView
    private lateinit var penerima: TextView
    private lateinit var syarat: TextView
    private lateinit var periode: TextView
    private lateinit var catt: TextView
    private lateinit var update: Button
    private lateinit var hapus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beasiswa_detail)

        initView()
        setValuesToViews()

        hideActionBar()
        btn_update.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("namaBeasiswa").toString(),
                intent.getStringExtra("penerimaBeasiswa").toString(),
                intent.getStringExtra("syaratBeasiswa").toString(),
                intent.getStringExtra("periodePendaftaran").toString(),
                intent.getStringExtra("cattTambahan").toString()
            )
        }
        btn_hapus.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("namaBeasiswa").toString()
            )
        }

    }
    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun openUpdateDialog(
        namaBeasiswa: String,
        penerimaBeasiswa: String,
        syaratBeasiswa: String,
        periodePendaftaran: String,
        cattTambahan: String

    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val uNama = mDialogView.findViewById<EditText>(R.id.et_nama)
        val uPenerima = mDialogView.findViewById<EditText>(R.id.et_penerima)
        val uSyarat = mDialogView.findViewById<EditText>(R.id.et_syarat)
        val uPeriode = mDialogView.findViewById<EditText>(R.id.et_periode)
        val uCatt = mDialogView.findViewById<EditText>(R.id.et_catatan)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        uNama.setText(intent.getStringExtra("namaBeasiswa").toString())
        uPenerima.setText(intent.getStringExtra("penerimaBeasiswa").toString())
        uSyarat.setText(intent.getStringExtra("syaratBeasiswa").toString())
        uPeriode.setText(intent.getStringExtra("periodePendaftaran").toString())
        uCatt.setText(intent.getStringExtra("cattTambahan").toString())

        mDialog.setTitle("Update $namaBeasiswa")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                uNama.text.toString(),
                uPenerima.text.toString(),
                uSyarat.text.toString(),
                uPeriode.text.toString(),
                uCatt.text.toString()
            )
            Toast.makeText(applicationContext, "Data berhasil di update", Toast.LENGTH_SHORT).show()

            nama.text = uNama.text.toString()
            penerima.text = uPenerima.text.toString()
            syarat.text = uSyarat.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        namaBeasiswa: String,
        penerimaBeasiswa: String,
        syaratBeasiswa: String,
        periodeBeasiswa: String,
        cattBeasiswa: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Beasiswa").child(namaBeasiswa)
        val Beasiswas = Beasiswa(namaBeasiswa, penerimaBeasiswa, syaratBeasiswa, periodeBeasiswa, cattBeasiswa)
        dbRef.setValue(Beasiswas)
    }

    private fun deleteRecord(
        namaBeasiswa: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Beasiswa").child(namaBeasiswa)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        nama = findViewById(R.id.desc_content)
        penerima = findViewById(R.id.info_target)
        syarat = findViewById(R.id.req_content)
        periode = findViewById(R.id.info_durasi)
        catt = findViewById(R.id.info_tunjangan)

        update = findViewById(R.id.btn_update)
        hapus = findViewById(R.id.btn_hapus)
    }

    private fun setValuesToViews() {
        nama.text = intent.getStringExtra("namaBeasiswa")
        penerima.text = intent.getStringExtra("penerimaBeasiswa")
        syarat.text = intent.getStringExtra("syaratBeasiswa")
        periode.text = intent.getStringExtra("periodePendaftaran")
        catt.text = intent.getStringExtra("cattTambahan")

    }
}