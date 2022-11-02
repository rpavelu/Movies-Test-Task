package com.ratushny.moviestest.presentation.screen.moviedetails

import com.ratushny.moviestest.domain.model.MovieDetails

data class MovieDetailsScreenState(
    val movie: MovieDetails,
    val state: State,
) {

    enum class State {
        LOADING,
        SUCCESS,
        ERROR,
        ;
    }
}