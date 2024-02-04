package com.beweaver.newsnest.ui.fragments.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.beweaver.newsnest.R

class NewsItemViewHolder(itemView: View): ViewHolder(itemView) {

    val cardView: CardView
    val newsItemIcon: ImageView
    val newsTitle: TextView
    val newsContent: TextView
    val shareIcon: ImageView
    val bookmarkIcon: ImageView
    val pubDate: TextView

    init {
        cardView = itemView.findViewById(R.id.cardView)
        newsItemIcon = itemView.findViewById(R.id.news_item_icon)
        newsTitle = itemView.findViewById(R.id.news_item_title)
        newsContent = itemView.findViewById(R.id.news_item_content)
        shareIcon = itemView.findViewById(R.id.news_item_share)
        bookmarkIcon = itemView.findViewById(R.id.news_item_bookmark)
        pubDate = itemView.findViewById(R.id.pub_date)
    }

}