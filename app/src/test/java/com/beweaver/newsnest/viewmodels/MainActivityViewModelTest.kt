package com.beweaver.newsnest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var repository: DBRepository

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher for tests

        repository = mock(DBRepository::class.java)
        viewModel = MainActivityViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after tests
        testCoroutineScope.cleanupTestCoroutines()
    }

    // Rest of your tests...

    @Test
    fun `saveNewsItem invokes repository insertNewsItem`() = testCoroutineScope.runBlockingTest {
        // Given
        val newsItem = createMockNewsItem()

        // When
        viewModel.saveNewsItem(newsItem)

        // Then
        verify(repository).insertNewsItem(newsItem)
    }

    @Test
    fun `deleteNewsItem invokes repository deleteNewsItem`() = testCoroutineScope.runBlockingTest {
        // Given
        val newsItem = createMockNewsItem()

        // When
        viewModel.deleteNewsItem(newsItem)

        // Then
        verify(repository).deleteNewsItem(newsItem)
    }

    @Test
    fun `deleteNewsItemByLink invokes repository deleteNewsItemByLink`() = testCoroutineScope.runBlockingTest {
        // Given
        val link = "https://example.com/news"

        // When
        viewModel.deleteNewsItemByLink(link)

        // Then
        verify(repository).deleteNewsItemByLink(link)
    }

    private fun createMockNewsItem(): DBNewsItem {
        return DBNewsItem(
            id = 1,
            title = "Test News",
            description = "This is a test news item.",
            link = "https://example.com/news/1",
            pubDate = "2023-01-01",
            creator = "Test Creator",
            image_url = "https://example.com/image.jpg"
        )
    }
}
