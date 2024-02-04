package com.beweaver.newsnest.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beweaver.newsnest.R
import com.beweaver.newsnest.data.BaseNewsRepository
import com.beweaver.newsnest.datamodels.RssFeedResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SelectedNewsViewModel @Inject constructor(private val repository: BaseNewsRepository) : ViewModel() {
    private val _selectedNews = MutableLiveData<RssFeedResponse>()
    val selectedNews: LiveData<RssFeedResponse> get() = _selectedNews

    @SuppressLint("StaticFieldLeak")
    @Inject
    lateinit var context: Context

    @SuppressLint("CheckResult")
    fun loadSelectedNews(newsName: String, onError: (String) -> Unit) {

        val observable = when(newsName) {
            "US"-> repository.getUSNews()
            "India" -> repository.getIndiaNews()
            "Business"->repository.getBusinessNews()
            "Sports" -> repository.getSportsNews()
            "Tech" -> repository.getTechNews()
            "Education" -> repository.getEducationNews()
            "Entertainment" -> repository.getEntertainmentNews()
            else -> {
                null
            }
        } ?: return

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    response -> _selectedNews.value = response
            }, {
                it.message?.let { error -> onError(error) }
                    ?: onError(context.getString(R.string.unknown_error))
                Log.e("Error:", it.message.toString())
            })
    }

}