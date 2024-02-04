package com.beweaver.newsnest.ui.fragments.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.beweaver.newsnest.R
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DatabaseEvent
import com.beweaver.newsnest.databinding.shareURL
import com.beweaver.newsnest.utils.DateTimeUtil
import com.beweaver.newsnest.utils.StringUtil
import com.bumptech.glide.Glide

class DBNewsListAdapter(private val bookmarkList: MutableList<DBNewsItem>,
                        private val onItemClick: (DBNewsItem) -> Unit,
                        private val removedAllItems:() -> Unit): RecyclerView.Adapter<NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_card_item, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookmarkList.size ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = bookmarkList.get(position)
        holder.cardView.setOnClickListener {
            if (newsItem != null) {
                onItemClick(newsItem)
            }
        }
        holder.shareIcon.setOnClickListener {
            newsItem.link?.let { it1 -> holder.shareIcon.context.shareURL(it1) }
        }
        holder.bookmarkIcon.setImageResource(R.drawable.baseline_delete_24)
        holder.bookmarkIcon.setOnClickListener {
            val intent = Intent(DatabaseEvent.DELETE_BOOKMARK)
            intent.putExtra(DatabaseEvent.DATABASE_TASK_ARG, newsItem)
            LocalBroadcastManager.getInstance(holder.bookmarkIcon.context).sendBroadcast(intent)
            bookmarkList.removeAt(position)
            notifyDataSetChanged()
            if (bookmarkList.isEmpty()) {
                removedAllItems()
            }
        }
        holder.newsTitle.text = newsItem?.title
        holder.newsContent.text = newsItem?.description?.let { StringUtil.removeTags(it) }
        holder.pubDate.text = holder.pubDate.context.getString(R.string.date_of_publish) + " " +newsItem?.pubDate?.let { time->
            DateTimeUtil.formatDate(time)
        }

        Glide.with(holder.itemView.context)
            .load(newsItem?.image_url)
            .override(300, 200)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.newsItemIcon)
    }
}