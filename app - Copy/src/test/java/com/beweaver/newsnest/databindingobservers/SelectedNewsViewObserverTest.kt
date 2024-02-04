package com.beweaver.newsnest.databindingobservers

import org.junit.Test
import org.junit.Assert.assertEquals
import androidx.databinding.Observable

class SelectedNewsViewObserverTest {

    @Test
    fun `loadingProgressBar_should_notify_property_change_when_updated`() {
        val observer = SelectedNewsViewObserver()
        var notifyPropertyChangedCalls = 0

        observer.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                notifyPropertyChangedCalls++
            }
        })

        observer.loadingProgressBar = true
        observer.loadingProgressBar = false

        assertEquals(2, notifyPropertyChangedCalls)
    }

    @Test
    fun `loadingProgressBar_should_update_value_when_set`() {
        val observer = SelectedNewsViewObserver()

        observer.loadingProgressBar = true
        assertEquals(true, observer.loadingProgressBar)

        observer.loadingProgressBar = false
        assertEquals(false, observer.loadingProgressBar)
    }
}
