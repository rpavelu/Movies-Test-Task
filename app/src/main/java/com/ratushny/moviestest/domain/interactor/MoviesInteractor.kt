package com.ratushny.moviestest.domain.interactor

import com.ratushny.moviestest.domain.api.MoviesRepositoryApi
import com.ratushny.moviestest.domain.model.Movie
import com.ratushny.moviestest.domain.model.MovieDetails

class MoviesInteractor(
    private val repository: MoviesRepositoryApi,
) {

    suspend fun loadMoviesList(): Result<List<Movie>> = repository.loadMoviesList()

    suspend fun loadMovieDetails(id: Long): Result<MovieDetails> = repository.loadMovieDetails(id)
}