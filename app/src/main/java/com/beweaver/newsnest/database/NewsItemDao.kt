package com.beweaver.newsnest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsItemDao {
    @Insert
    fun insert(data: DBNewsItem)

    @Delete
    fun delete(data: DBNewsItem)

    @Query("SELECT * FROM news_items")
    fun getAllData(): List<DBNewsItem>

    @Query("SELECT COUNT(*) FROM news_items WHERE link = :link")
    fun getCountByLink(link: String): Int

    @Query("DELETE FROM news_items WHERE link = :link")
    fun deleteItemByLink(link: String)

}