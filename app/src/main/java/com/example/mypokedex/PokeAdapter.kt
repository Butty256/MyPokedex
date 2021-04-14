package com.example.mypokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//, private val listener: ListListener
class PokeAdapter(private val list: List<PokeImage>) : RecyclerView.Adapter<PokeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PokeHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        holder.idView.text = list[position].id.toString()
        holder.nameView.text = list[position].name
        //holder.itemView.setOnClickListener {
        //    listener.onClickRow(it, list[position])
        //}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, pokeImage: PokeImage)
    }
}
