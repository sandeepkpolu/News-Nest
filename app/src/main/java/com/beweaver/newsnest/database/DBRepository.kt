package com.beweaver.newsnest.database

import io.reactivex.Single

interface DBRepository {

    suspend fun insertNewsItem(data: DBNewsItem)

    suspend fun deleteNewsItem(data: DBNewsItem)

    suspend fun deleteNewsItemByLink(link: String)

    fun getAllBookmarks(): Single<List<DBNewsItem>>

    fun getCountByLink(link: String): Single<Int>
}