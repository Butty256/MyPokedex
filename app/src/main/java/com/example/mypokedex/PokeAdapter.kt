package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter(private val data: DexInfo) : RecyclerView.Adapter<PokeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PokeHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        holder.idView.text = data.pokemon_entries[position].entry_number.toString()
        holder.nameView.text = data.pokemon_entries[position].pokemon_species.name
    }

    override fun getItemCount(): Int {
        return data.pokemon_entries.size
    }
}
