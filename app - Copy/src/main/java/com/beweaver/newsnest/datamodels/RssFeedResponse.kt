package com.beweaver.newsnest.datamodels

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeedResponse @JvmOverloads constructor(
    @field:Element(name = "channel")
    var channel: Channel? = null
)