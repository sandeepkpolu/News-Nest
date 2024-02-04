package com.beweaver.newsnest.viewmodels

import  com.beweaver.newsnest.BR
import androidx.databinding.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ToolbarViewModelTest {

    private lateinit var viewModel: ToolbarViewModel

    @Before
    fun setUp() {
        viewModel = ToolbarViewModel()
    }

    @Test
    fun `set title updates property and notifies observers`() {
        // Given
        val newTitle = "New Title"
        val mockObserver = MockObserver()
        viewModel.addOnPropertyChangedCallback(mockObserver)

        // When
        viewModel.title = newTitle

        // Then
        assertEquals(newTitle, viewModel.title)
        assertEquals(BR.title, mockObserver.changedPropertyId)
    }

    @Test
    fun `set backButtonVisibility updates property and notifies observers`() {
        // Given
        val newVisibility = true
        val mockObserver = MockObserver()
        viewModel.addOnPropertyChangedCallback(mockObserver)

        // When
        viewModel.backButtonVisibility = newVisibility

        // Then
        assertEquals(newVisibility, viewModel.backButtonVisibility)
        assertEquals(BR.backButtonVisibility, mockObserver.changedPropertyId)
    }

    private class MockObserver : Observable.OnPropertyChangedCallback() {
        var changedPropertyId: Int = 0

        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            changedPropertyId = propertyId
        }
    }
}
