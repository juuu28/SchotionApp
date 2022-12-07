package com.example.schotion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class BeasiswaAdapter(private var BeasiswaList : ArrayList<Beasiswa>): RecyclerView.Adapter<BeasiswaAdapter.BeasiswaViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeasiswaViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.elemen_beasiswa,
            parent,false)
        return BeasiswaViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: BeasiswaViewHolder, position: Int) {

        val currentitem = BeasiswaList[position]

        holder.judulBeasiswa.text = currentitem.namaBeasiswa

    }

    override fun getItemCount(): Int {
        return BeasiswaList.size
    }


    class BeasiswaViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val judulBeasiswa : TextView = itemView.findViewById(R.id.beasiswa_title)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}