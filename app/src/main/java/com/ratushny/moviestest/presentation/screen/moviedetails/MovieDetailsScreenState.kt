package com.ratushny.moviestest.presentation.screen.moviedetails

import com.ratushny.moviestest.domain.model.MovieDetails

data class MovieDetailsScreenState(
    val movie: Result<MovieDetails>,
    val isLoading: Boolean,
) {
    companion object {
        val default
            get() = MovieDetailsScreenState(
                movie = Result.success(
                    MovieDetails(
                        title = "",
                        originalTitle = "",
                        tagline = "",
                        posterPath = "",
                        overview = "",
                    )
                ),
                isLoading = true,
            )
    }
}