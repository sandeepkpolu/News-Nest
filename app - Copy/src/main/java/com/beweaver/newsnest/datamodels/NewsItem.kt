package com.beweaver.newsnest.datamodels

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "item", strict = false)
data class NewsItem @JvmOverloads constructor(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null,

    @field:Element(name = "creator", required = false)
    var creator: String? = null,

    @field:Element(name = "enclosure", required = false)
    var enclosure: Enclosure? = null
) : Serializable
