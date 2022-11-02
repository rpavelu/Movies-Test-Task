package com.ratushny.moviestest.data.network.model.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "results") val results: List<MovieResponse>,
)