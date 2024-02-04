package com.beweaver.newsnest.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DBRepository

class BookmarksViewModel(private val newsItemDBRepository: DBRepository) : ViewModel() {
    @VisibleForTesting
    private val _dbNewsItems = MutableLiveData<List<DBNewsItem>>()
    val bookmarkNewsItems: LiveData<List<DBNewsItem>> get() = _dbNewsItems

    @SuppressLint("CheckResult")
    fun loadBookmarkNews() {
        newsItemDBRepository.getAllBookmarks()
            .subscribe({
                _dbNewsItems.value = it
            }, {
                Log.e("Error", it.message.toString())
            })
    }
}