package com.beweaver.newsnest.databinding

import android.content.Context
import androidx.core.app.ShareCompat

fun Context.shareURL(url: String) {
    ShareCompat.IntentBuilder(this)
        .setType("text/plain")
        .setChooserTitle("Share news article")
        .setText(url)
        .startChooser()
}