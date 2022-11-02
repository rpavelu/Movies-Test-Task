package com.ratushny.moviestest.presentation.screen.moviedetails

import androidx.lifecycle.viewModelScope
import com.ratushny.moviestest.domain.interactor.MoviesInteractor
import com.ratushny.moviestest.presentation.extensions.update
import com.ratushny.moviestest.presentation.screen.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val moviesInteractor: MoviesInteractor,
) : BaseViewModel<MovieDetailsScreenState>() {

    init {
        _screenState.value = MovieDetailsScreenState.default
    }

    fun loadMovieDetails(id: Long) {
        viewModelScope.launch {
            val movies = moviesInteractor.loadMovieDetails(id)

            _screenState.update {
                copy(
                    movie = movies,
                    isLoading = false,
                )
            }
        }
    }
}