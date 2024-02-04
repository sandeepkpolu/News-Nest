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

class WorldNewsViewModel @Inject constructor(private val repository: BaseNewsRepository) : ViewModel() {
    private val _worldNews = MutableLiveData<RssFeedResponse>()
    val worldNews: LiveData<RssFeedResponse> get() = _worldNews

    @SuppressLint("StaticFieldLeak")
    @Inject
    lateinit var context: Context

    @SuppressLint("CheckResult")
    fun loadWorldNews(onError: (String) -> Unit) {
        repository.getWorldNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    response -> _worldNews.value = response
            }, {
                it.message?.let { error -> onError(error) }
                    ?: onError(context.getString(R.string.unknown_error))
                Log.e("Error:", it.message.toString())
            })
    }

}
