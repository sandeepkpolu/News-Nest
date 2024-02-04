package com.beweaver.newsnest.utils

import com.beweaver.newsnest.utils.DateTimeUtil.Companion.formatDate
import org.junit.Test
import org.junit.Assert.assertEquals

class DateFormatterTest {

    @Test
    fun `formatDate_with_EEE_MMM_yyyy_HH_mm_ss_Z_format_should_parse_and_format_correctly`() {
        val inputDate = "Mon, 23 Oct 2023 13:45:30 +0000"
        val expectedOutput = "23 Oct 2023, 01:45 PM"
        val actualOutput = formatDate(inputDate)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `formatDate_with_yyyy_MM_dd'T'HH_mm_ssXXX_format_should_parse_and_format_correctly`() {
        val inputDate = "2023-10-23T13:45:30+00:00"
        val expectedOutput = "23 Oct 2023, 01:45 PM"
        val actualOutput = formatDate(inputDate)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `formatDate_with_invalid_date_should_return_empty_string`() {
        val inputDate = "Invalid date format"
        val expectedOutput = ""
        val actualOutput = formatDate(inputDate)

        assertEquals(expectedOutput, actualOutput)
    }
}
