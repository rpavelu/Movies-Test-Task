package com.ratushny.moviestest.presentation.screen.movielist

import com.ratushny.moviestest.domain.model.Movie

data class MovieListScreenState(
    val movies: List<Movie>,
    val state: State,
) {

    enum class State {
        LOADING,
        SUCCESS,
        ERROR,
        ;
    }
}


