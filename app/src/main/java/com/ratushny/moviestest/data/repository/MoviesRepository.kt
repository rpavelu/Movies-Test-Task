package com.ratushny.moviestest.data.repository

import com.ratushny.moviestest.BuildConfig
import com.ratushny.moviestest.data.network.MoviesServiceV3
import com.ratushny.moviestest.data.network.mapper.convertToAppEntity
import com.ratushny.moviestest.domain.api.MoviesRepositoryApi
import com.ratushny.moviestest.domain.model.Movie
import com.ratushny.moviestest.domain.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val service: MoviesServiceV3,
) : MoviesRepositoryApi {

    override suspend fun loadMoviesList(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            runCatching {
                service.loadMoviesList(BuildConfig.API_KEY)
                    .results
                    .map { it.convertToAppEntity() }
            }
        }

    override suspend fun loadMovieDetails(id: Long): Result<MovieDetails> =
        withContext(Dispatchers.IO) {
            runCatching {
                service.loadMovieDetails(id.toString(), BuildConfig.API_KEY)
                    .convertToAppEntity()
            }
        }
}