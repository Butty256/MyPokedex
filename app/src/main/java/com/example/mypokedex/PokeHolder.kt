package com.example.mypokedex

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val idView: TextView = itemView.findViewById(R.id.id_text)
    val nameView: TextView = itemView.findViewById(R.id.name_text)
}