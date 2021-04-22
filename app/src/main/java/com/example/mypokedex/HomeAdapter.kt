package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val data: PokeDex) : RecyclerView.Adapter<HomeHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return HomeHolder(rowView)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.entryView.text = data.pokemon_entries[position].entry_number.toString()
        holder.nameView.text = data.pokemon_entries[position].pokemon_species.name.capitalize()
        // URL の設定がガサツ
        GlideApp.with(holder.iconView)
            .load(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        holder.entryView.text + ".png"
            )
            .into(holder.iconView)

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(it, position, data.pokemon_entries[position].entry_number)
        }
    }

    override fun getItemCount(): Int {
        return data.pokemon_entries.size
    }

    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, entry: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}
