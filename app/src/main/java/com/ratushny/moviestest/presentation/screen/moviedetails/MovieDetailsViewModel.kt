package com.ratushny.moviestest.presentation.screen.moviedetails

import androidx.lifecycle.viewModelScope
import com.ratushny.moviestest.domain.interactor.MoviesInteractor
import com.ratushny.moviestest.domain.model.MovieDetails
import com.ratushny.moviestest.presentation.extensions.update
import com.ratushny.moviestest.presentation.screen.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MovieDetailsViewModel(
    private val moviesInteractor: MoviesInteractor,
) : BaseViewModel<MovieDetailsScreenState>() {

    private val emptyMovie by lazy {
        MovieDetails(
            title = "",
            originalTitle = "",
            tagline = "",
            posterPath = "",
            overview = "",
        )
    }

    init {
        _screenState.value = MovieDetailsScreenState(
            emptyMovie,
            state = MovieDetailsScreenState.State.LOADING,
        )
    }

    private var movieDetails by Delegates.observable(
        initialValue = emptyMovie,
    ) { _, _, _ ->
        updateMovieDetails()
    }

    fun loadMovieDetails(id: Long) {
        viewModelScope.launch {
            try {
                movieDetails = moviesInteractor.loadMovieDetails(id)
            } catch (error: Exception) {
                movieDetails = emptyMovie
                _screenState.update { copy(state = MovieDetailsScreenState.State.ERROR) }
            }
        }
    }

    private fun updateMovieDetails() {
        _screenState.update {
            copy(
                movie = movieDetails,
                state = MovieDetailsScreenState.State.SUCCESS,
            )
        }
    }
}