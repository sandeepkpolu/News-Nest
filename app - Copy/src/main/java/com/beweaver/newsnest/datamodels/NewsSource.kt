package com.beweaver.newsnest.datamodels

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class NewsSource(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("url")
    val url: String)