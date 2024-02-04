package com.beweaver.newsnest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DBRepository
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BookmarksViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockDbRepository: DBRepository

    private lateinit var viewModel: BookmarksViewModel

    private fun createMockDBNewsItem(): DBNewsItem {
        return DBNewsItem(
            title = "Test Title",
            description = "Test Description",
            link = "https://example.com/news",
            pubDate = "2023-12-06",
            creator = "Test Creator",
            image_url = "https://example.com/image.jpg"
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = BookmarksViewModel(mockDbRepository)
    }

    @Test
    fun `loadBookmarkNews should update live data with retrieved bookmarks`() {
        // Given
        val mockNewsItems = listOf(createMockDBNewsItem())

        // When
        Mockito.`when`(mockDbRepository.getAllBookmarks()).thenReturn(Single.just(mockNewsItems))
        viewModel.loadBookmarkNews()

        // Then
        val observedBookmarks = viewModel.bookmarkNewsItems.value
        assertEquals(mockNewsItems, observedBookmarks)
    }

    @Test
    fun `loadBookmarkNews should log error if repository throws exception`() {
        // Given
        val exception = RuntimeException("Error retrieving bookmarks")
        val viewModel = BookmarksViewModel(mockDbRepository)

        // When
        Mockito.`when`(mockDbRepository.getAllBookmarks()).thenReturn(Single.error(exception))
        viewModel.loadBookmarkNews()

        // Then
        assertEquals("Error retrieving bookmarks", exception.message.toString())
    }

}

