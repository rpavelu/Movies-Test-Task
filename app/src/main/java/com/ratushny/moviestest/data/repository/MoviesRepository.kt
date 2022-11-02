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

    override suspend fun loadMoviesList(): List<Movie> = withContext(Dispatchers.IO) {
        service.loadMoviesList(BuildConfig.API_KEY).results.map { it.convertToAppEntity() }
    }

    override suspend fun loadMovieDetails(id: Long): MovieDetails = withContext(Dispatchers.IO) {
        service.loadMovieDetails(id.toString(), BuildConfig.API_KEY).convertToAppEntity()
    }
}