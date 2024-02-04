package com.beweaver.newsnest.viewmodels

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.io.ByteArrayInputStream

class MoreNewsViewModelTest {

    private lateinit var viewModel: MoreNewsViewModel
    private lateinit var context: Context

    @Before
    fun setup() {
        viewModel = MoreNewsViewModel()
        context = mock()

        // Mock the getAssets() method of the Context
        `when`(context.assets).thenReturn(mock())
    }

    @Test
    fun `loadItems success`() {
        // Given
        val json = """
            [
              {
                "name": "US",
                "url": "https://timesofindia.indiatimes.com/rssfeeds_us/72258322.cms"
              },
              {
                "name": "India",
                "url": "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms"
              },
              {
                "name": "Business",
                "url": "https://timesofindia.indiatimes.com/rssfeeds/1898055.cms"
              }
            ]
        """.trimIndent()

        val inputStream = ByteArrayInputStream(json.toByteArray())
        `when`(context.assets.open("news_sources.json")).thenReturn(inputStream)

        // When
        val result = viewModel.loadItems(context)

        // Then
        assertEquals(3, result.size) // Check the expected number of items

        // Add more assertions based on the expected content of the JSON file
        assertEquals("US", result[0].name)
        assertEquals("https://timesofindia.indiatimes.com/rssfeeds_us/72258322.cms", result[0].url)

        assertEquals("India", result[1].name)
        assertEquals("https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms", result[1].url)

        assertEquals("Business", result[2].name)
        assertEquals("https://timesofindia.indiatimes.com/rssfeeds/1898055.cms", result[2].url)
    }

    @Test
    fun `loadItems with empty JSON`() {
        // Given
        val json = "[]"
        val inputStream = ByteArrayInputStream(json.toByteArray())
        `when`(context.assets.open("news_sources.json")).thenReturn(inputStream)

        // When
        val result = viewModel.loadItems(context)

        // Then
        assertEquals(0, result.size) // Check that the result is empty
    }

}
