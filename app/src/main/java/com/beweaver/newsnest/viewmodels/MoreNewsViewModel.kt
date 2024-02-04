package com.beweaver.newsnest.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.beweaver.newsnest.datamodels.NewsSource
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class MoreNewsViewModel(): ViewModel() {

   fun loadItems(context: Context): List<NewsSource> {
       val json: String = context.assets.open("news_sources.json").bufferedReader().use {
           it.readText()
       }
       return ObjectMapper().readValue(json, object : TypeReference<List<NewsSource>>() {})
   }

}