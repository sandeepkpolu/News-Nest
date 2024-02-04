package com.beweaver.newsnest.datamodels

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(entry = "item", inline = true, required = false)
    var items: List<NewsItem>? = null
)