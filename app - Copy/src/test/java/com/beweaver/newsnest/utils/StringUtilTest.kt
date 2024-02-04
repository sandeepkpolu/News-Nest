package com.beweaver.newsnest.utils

import org.junit.Assert
import org.junit.Test


class StringUtilTest {

    @Test
    fun `removeImgTags should remove "img" tags from the input string`() {
        val input = "<img src='image.jpg'/>This is some text with an image."
        val expectedOutput = "This is some text with an image."
        val actualOutput = StringUtil.removeImgTags(input)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `removeATags should remove "a" tags from the input string`() {
        val input = "Click <a href='https://example.com'>here</a> for more information."
        val expectedOutput = "Click  for more information."
        val actualOutput = StringUtil.removeATags(input)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `removeTags should remove both img and "a" tags from the input string`() {
        val input = "This is a sample <a href='https://example.com'>text</a>with tags."
        val expectedOutput = "This is a sample with tags."
        val actualOutput = StringUtil.removeATags(input)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `removeTags should handle input with no tags`() {
        val input = "This is a text without any tags."
        val expectedOutput = "This is a text without any tags."
        val actualOutput = StringUtil.removeATags(input)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

}
