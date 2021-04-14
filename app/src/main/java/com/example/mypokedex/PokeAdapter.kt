package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter(private val array: Array<PokeImage>) : RecyclerView.Adapter<PokeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PokeHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        holder.idView.text = array[position].id.toString()
        holder.nameView.text = array[position].name
    }

    override fun getItemCount(): Int {
        return array.size
    }
}
