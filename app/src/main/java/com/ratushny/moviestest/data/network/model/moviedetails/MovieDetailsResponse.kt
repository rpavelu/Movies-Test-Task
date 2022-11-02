package com.ratushny.moviestest.data.network.model.moviedetails

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    @Json(name = "title") val title: String?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "overview") val overview: String?,
)