package com.beweaver.newsnest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBNewsItem::class], version = 1, exportSchema = false)
abstract class NewsItemDatabase: RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao

    companion object {
        private const val DATABASE_NAME = "news_item_database"
        @Volatile
        private var instance: NewsItemDatabase? = null

        fun getInstance(context: Context): NewsItemDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NewsItemDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                NewsItemDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}