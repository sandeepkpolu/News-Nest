package com.beweaver.newsnest.database

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsItemDBRepository(private val dao: NewsItemDao): DBRepository {

    override suspend fun insertNewsItem(data: DBNewsItem) {
        withContext(Dispatchers.IO) {
            dao.insert(data)
        }
    }

    override suspend fun deleteNewsItem(data: DBNewsItem) {
        withContext(Dispatchers.IO) {
            dao.delete(data)
        }
    }

    override suspend fun deleteNewsItemByLink(link: String) {
        withContext(Dispatchers.IO) {
            dao.deleteItemByLink(link)
        }
    }

    @SuppressLint("CheckResult")
    override fun getAllBookmarks(): Single<List<DBNewsItem>> {
        return Single.create<List<DBNewsItem>> {
            try {
                val list = dao.getAllData()
                it.onSuccess(list)
            } catch (e: Exception) {
                it.onError(e)
            }

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCountByLink(link: String): Single<Int> {
        return Single.create<Int> {
            try {
                val count = dao.getCountByLink(link)
                it.onSuccess(count)
            } catch (e: Exception) {
                it.onError(e)
            }

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}