package com.beweaver.newsnest.utils

import com.beweaver.newsnest.R

class ImageUtil {

    companion object {
        private val imageResourceMap: Map<String, Int> = mapOf(
            "US" to R.drawable.ic_usa_icon,
            "India" to R.drawable.ic_india_icon,
            "Business" to R.drawable.ic_business_icon,
            "Sports" to R.drawable.ic_sports_icon,
            "Tech" to R.drawable.ic_tech_icon,
            "Education" to R.drawable.ic_education_icon,
            "Entertainment" to R.drawable.ic_entertainment_icon
        )

        fun getImageResourceId(name: String): Int {
            return imageResourceMap[name] ?: 0
        }
    }
}