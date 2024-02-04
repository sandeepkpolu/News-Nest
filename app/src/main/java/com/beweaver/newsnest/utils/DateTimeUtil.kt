package com.beweaver.newsnest.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class DateTimeUtil {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(inputDate: String): String {
            val inputFormatter1 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
            val inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
            val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.ENGLISH)

            return try {
                val parsedDate1 = LocalDateTime.parse(inputDate, inputFormatter1)
                outputFormatter.format(parsedDate1)
            } catch (e1: DateTimeParseException) {
                try {
                    // If parsing using the first format fails, try the second format
                    val parsedDate2 = LocalDateTime.parse(inputDate, inputFormatter2)
                    outputFormatter.format(parsedDate2)
                } catch (e2: Exception) {
                    // Both parsing attempts failed
                    ""
                }
            }
        }

    }

}