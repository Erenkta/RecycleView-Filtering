package com.example.recycleviewfiltering

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val myText:TextView
    init{
        myText = itemView.findViewById(R.id.textView)
    }
}