package com.beweaver.newsnest.databindingobservers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import javax.inject.Inject

class TopNewsViewObserver @Inject constructor() : BaseObservable() {

    @get:Bindable
    var loadingProgressBar: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingProgressBar)
        }

}