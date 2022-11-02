package com.ratushny.moviestest.data.network.mapper

import com.ratushny.moviestest.BuildConfig
import com.ratushny.moviestest.data.converter.LocalDateJsonConverter
import com.ratushny.moviestest.data.network.model.movie.MovieResponse
import com.ratushny.moviestest.data.network.model.moviedetails.MovieDetailsResponse
import com.ratushny.moviestest.domain.model.Movie
import com.ratushny.moviestest.domain.model.MovieDetails
import java.time.LocalDate

fun MovieResponse.convertToAppEntity(): Movie = Movie(
    id = id ?: 0,
    title = title ?: "",
    posterPath = BuildConfig.MOVIE_IMAGE_W500_URL + posterPath,
    releaseDate = LocalDateJsonConverter().fromJson(releaseDate ?: "") ?: LocalDate.MIN,
    voteAverage = voteAverage ?: 0.0f,
)

fun MovieDetailsResponse.convertToAppEntity(): MovieDetails = MovieDetails(
    title = title ?: "",
    originalTitle = originalTitle ?: "",
    tagline = tagline ?: "",
    posterPath = BuildConfig.MOVIE_IMAGE_ORIGINAL_URL + posterPath,
    overview = overview ?: "",
)