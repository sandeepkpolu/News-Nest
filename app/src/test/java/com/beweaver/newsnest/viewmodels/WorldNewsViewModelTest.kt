package com.beweaver.newsnest.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.beweaver.newsnest.data.BaseNewsRepository
import com.beweaver.newsnest.datamodels.Channel
import com.beweaver.newsnest.datamodels.NewsItem
import com.beweaver.newsnest.datamodels.RssFeedResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class WorldNewsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: WorldNewsViewModel
    private lateinit var repository: BaseNewsRepository
    private lateinit var context: Context
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        repository = mock()
        context = mock()
        testScheduler = TestScheduler()

        // Initialize RxAndroidPlugins for testing
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        // Set up RxJavaPlugins to use the test scheduler for IO operations
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }


        viewModel = WorldNewsViewModel(repository)
        viewModel.context = context
    }

    @Test
    fun `loadWorldNews success`() {
        // Given
        val rssFeedResponse = createMockRssFeedResponse()
        Mockito.`when`(repository.getWorldNews()).thenReturn(Observable.just(rssFeedResponse))

        // Create Observer mock
        val observer: Observer<RssFeedResponse> = mock()

        // Observe LiveData from ViewModel
        viewModel.worldNews.observeForever(observer)

        // When
        viewModel.loadWorldNews { TestCase.fail("Error callback should not be called") }
        testScheduler.triggerActions()

        // Then
        // Verify that the repository method is called
        verify(repository).getWorldNews()

        // Verify that the LiveData is updated
        verify(observer).onChanged(rssFeedResponse)
    }

    @Test
    fun `loadWorldNews error`() {
        // Given
        val errorMessage = "Test error"
        Mockito.`when`(repository.getWorldNews()).thenReturn(Observable.error(Throwable(errorMessage)))

        // Create Observer mock
        val observer: Observer<RssFeedResponse> = mock()

        // Observe LiveData from ViewModel
        viewModel.worldNews.observeForever(observer)

        // When
        viewModel.loadWorldNews { errorMsg ->
            // Verify that the error callback is called with the correct message
            assert(errorMsg == errorMessage)
        }
        testScheduler.triggerActions()

        // Then
        // Verify that the repository method is called
        verify(repository).getWorldNews()

        // Verify that the LiveData is not updated
        verify(observer, never()).onChanged(any())
    }

    private fun createMockRssFeedResponse(): RssFeedResponse {
        // Create a mock RssFeedResponse for testing
        // You can customize this based on your needs
        return RssFeedResponse(Channel(listOf(NewsItem(title = "Test Title"))))
    }

}