package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokeAdapter(private val data: DexInfo) : RecyclerView.Adapter<PokeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PokeHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        holder.idView.text = data.pokemon_entries[position].entry_number.toString()
        holder.nameView.text = data.pokemon_entries[position].pokemon_species.name.capitalize()
        Glide.with(holder.iconView)
            .load("https://avatars.githubusercontent.com/u/39516256?v=4")
            .into(holder.iconView)
    }

    override fun getItemCount(): Int {
        return data.pokemon_entries.size
    }
}
