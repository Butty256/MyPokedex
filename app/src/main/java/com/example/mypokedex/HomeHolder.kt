package com.example.mypokedex

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val entryView: TextView = itemView.findViewById(R.id.entry_text)
    val nameView: TextView = itemView.findViewById(R.id.name_text)
    var iconView: ImageView = itemView.findViewById(R.id.icon_image)
}