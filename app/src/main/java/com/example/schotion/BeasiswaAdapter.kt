package com.example.schotion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeasiswaAdapter(private val BeasiswaList : ArrayList<Beasiswa>) : RecyclerView.Adapter<BeasiswaAdapter.BeasiswaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeasiswaViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.elemen_beasiswa,
            parent,false)
        return BeasiswaViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: BeasiswaViewHolder, position: Int) {

        val currentitem = BeasiswaList[position]

        holder.judulBeasiswa.text = currentitem.namaBeasiswa
    }

    override fun getItemCount(): Int {

        return BeasiswaList.size
    }


    class BeasiswaViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val judulBeasiswa : TextView = itemView.findViewById(R.id.beasiswa_title)
    }

}