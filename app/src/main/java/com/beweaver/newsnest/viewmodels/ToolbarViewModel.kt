package com.beweaver.newsnest.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import  com.beweaver.newsnest.BR

class ToolbarViewModel : BaseObservable() {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var backButtonVisibility = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.backButtonVisibility)
        }

}