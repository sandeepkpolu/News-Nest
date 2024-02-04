package com.beweaver.newsnest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DBRepository
import kotlinx.coroutines.launch


class MainActivityViewModel(private val repository: DBRepository) : ViewModel() {

    fun saveNewsItem(item: DBNewsItem) {
        viewModelScope.launch {
            repository.insertNewsItem(item)
        }
    }

    fun deleteNewsItem(item: DBNewsItem) {
        viewModelScope.launch {
            repository.deleteNewsItem(item)
        }
    }

    fun deleteNewsItemByLink(link: String) {
        viewModelScope.launch {
            repository.deleteNewsItemByLink(link)
        }
    }

}