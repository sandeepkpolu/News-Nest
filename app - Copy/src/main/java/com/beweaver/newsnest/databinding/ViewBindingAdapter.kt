package com.beweaver.newsnest.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibility")
fun View.bindVisibility(value: Boolean) {
    visibility = if (value)  View.VISIBLE else View.GONE
}