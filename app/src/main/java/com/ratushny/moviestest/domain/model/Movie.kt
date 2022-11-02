package com.ratushny.moviestest.domain.model

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val releaseDate: LocalDate,
    val voteAverage: Float,
)