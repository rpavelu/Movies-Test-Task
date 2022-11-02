package com.ratushny.moviestest.data.network

import com.ratushny.moviestest.data.network.model.movie.MovieListResponse
import com.ratushny.moviestest.data.network.model.moviedetails.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServiceV3 {

    @GET("discover/movie")
    suspend fun loadMoviesList(@Query("api_key") key: String): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") id: String,
        @Query("api_key") key: String,
    ): MovieDetailsResponse
}