package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter(private val list: List<PokeImage>, private val listener: ListListener) : RecyclerView.Adapter<PokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PokeViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.idView.text = list[position].id.toString()
        holder.nameView.text = list[position].name
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, pokeImage: PokeImage)
    }
}
