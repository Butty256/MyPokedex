package com.example.mypokedex

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val idView: TextView = itemView.findViewById(R.id.id_text)
    val nameView: TextView = itemView.findViewById(R.id.name_text)
    GlideApp.with(this)
    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Shoyu_ramen%2C_at_Kasukabe_Station_%282014.05.05%29_2.jpg/520px-Shoyu_ramen%2C_at_Kasukabe_Station_%282014.05.05%29_2.jpg")
    .placeholder(ColorDrawable(Color.BLUE))
    .error(ColorDrawable(Color.RED))
    .transform(RoundedCorners(32))
    .into(findViewById(R.id.image_view))
}