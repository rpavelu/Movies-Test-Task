package com.ratushny.moviestest.presentation.screen.movielist

import com.ratushny.moviestest.domain.model.Movie

data class MovieListScreenState(
    val moviesResult: Result<List<Movie>>,
    val isLoading: Boolean,
) {
    companion object {
        val default
            get() = MovieListScreenState(
                moviesResult = Result.success(emptyList()),
                isLoading = true,
            )
    }
}


