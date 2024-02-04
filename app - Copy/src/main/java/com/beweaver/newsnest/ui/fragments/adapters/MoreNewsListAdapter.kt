package com.beweaver.newsnest.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beweaver.newsnest.R
import com.beweaver.newsnest.datamodels.NewsSource
import com.beweaver.newsnest.utils.ImageUtil

class MoreNewsListAdapter(private val newsSources: List<NewsSource>, private val onItemClick: (NewsSource) -> Unit) : RecyclerView.Adapter<MoreNewsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreNewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.more_news_list_item, parent, false)
        return MoreNewsItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsSources.size
    }

    override fun onBindViewHolder(holder: MoreNewsItemViewHolder, position: Int) {
        val newsSource = newsSources[position]
        holder.itemView.setOnClickListener {
            onItemClick(newsSource)
        }
        holder.titleView.text = newsSource.name
        holder.imageView.setImageResource(ImageUtil.getImageResourceId(newsSource.name))
    }

}