package com.ratushny.moviestest.domain.api

import com.ratushny.moviestest.domain.model.Movie
import com.ratushny.moviestest.domain.model.MovieDetails

interface MoviesRepositoryApi {

    suspend fun loadMoviesList(): List<Movie>

    suspend fun loadMovieDetails(id: Long): MovieDetails
}