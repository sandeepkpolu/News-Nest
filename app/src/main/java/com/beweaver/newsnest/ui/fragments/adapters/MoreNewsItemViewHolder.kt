package com.beweaver.newsnest.ui.fragments.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.beweaver.newsnest.R

class MoreNewsItemViewHolder(itemView: View): ViewHolder(itemView) {
    val imageView: ImageView
    val titleView: TextView

    init {
        imageView = itemView.findViewById(R.id.news_icon)
        titleView = itemView.findViewById(R.id.title_text)
    }
}