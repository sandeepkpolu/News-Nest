package com.beweaver.newsnest.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import com.beweaver.newsnest.R

class ImageUtilTest {

    @Test
    fun `getImageResourceId_with_valid_name_should_return_correct_resource_id`() {
        val name = "US"
        val expectedResourceId = R.drawable.ic_usa_icon
        val actualResourceId = ImageUtil.getImageResourceId(name)

        assertEquals(expectedResourceId, actualResourceId)
    }

    @Test
    fun `getImageResourceId_with_invalid_name_should_return_default_resource_id`() {
        val name = "InvalidName"
        val expectedResourceId = 0
        val actualResourceId = ImageUtil.getImageResourceId(name)

        assertEquals(expectedResourceId, actualResourceId)
    }
}