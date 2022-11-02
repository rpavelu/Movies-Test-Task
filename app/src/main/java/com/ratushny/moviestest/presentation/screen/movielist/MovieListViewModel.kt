package com.ratushny.moviestest.presentation.screen.movielist

import androidx.lifecycle.viewModelScope
import com.ratushny.moviestest.domain.interactor.MoviesInteractor
import com.ratushny.moviestest.presentation.extensions.update
import com.ratushny.moviestest.presentation.screen.BaseViewModel
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val moviesInteractor: MoviesInteractor,
) : BaseViewModel<MovieListScreenState>() {

    init {
        _screenState.value = MovieListScreenState.default
    }

    override fun onAttached() {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            loadMoviesList()
        }
    }

    private suspend fun loadMoviesList() {
        _screenState.update { copy(isLoading = true) }
        val moviesResult = moviesInteractor.loadMoviesList()

        _screenState.update {
            copy(
                moviesResult = moviesResult,
                isLoading = false,
            )
        }
    }
}