package com.beweaver.newsnest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news_items")
data class DBNewsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val creator: String,
    val image_url: String
) : Serializable