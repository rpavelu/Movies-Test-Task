package com.ratushny.moviestest.data.converter

import com.squareup.moshi.FromJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class LocalDateJsonConverter {

    private val formatterYMD: DateTimeFormatter by lazy {
        DateTimeFormatter.ofPattern(DATE_PATTERN).withLocale(Locale.ENGLISH)
    }

    @FromJson
    fun fromJson(json: String): LocalDate? {
        val stringDate = json.trim()
        return try {
            formatterYMD.parse(stringDate, LocalDate::from)
        } catch (e: DateTimeParseException) {
            try {
                LocalDate.parse(stringDate)
            } catch (ex: DateTimeParseException) {
                null
            }
        }
    }

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"
    }
}
