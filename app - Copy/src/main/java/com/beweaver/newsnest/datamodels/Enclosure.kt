package com.beweaver.newsnest.datamodels

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure @JvmOverloads constructor(
    @field:Attribute(name = "type", required = false)
    var type: String? = null,

    @field:Attribute(name = "length", required = false)
    var length: String? = null,

    @field:Attribute(name = "url", required = false)
    var url: String? = null
)