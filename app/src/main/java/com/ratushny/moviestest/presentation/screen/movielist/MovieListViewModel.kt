package com.ratushny.moviestest.presentation.screen.movielist

import androidx.lifecycle.viewModelScope
import com.ratushny.moviestest.domain.interactor.MoviesInteractor
import com.ratushny.moviestest.domain.model.Movie
import com.ratushny.moviestest.presentation.extensions.update
import com.ratushny.moviestest.presentation.screen.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MovieListViewModel(
    private val moviesInteractor: MoviesInteractor,
) : BaseViewModel<MovieListScreenState>() {

    init {
        _screenState.value = MovieListScreenState(
            movies = emptyList(),
            state = MovieListScreenState.State.LOADING,
        )
    }

    private var moviesList by Delegates.observable(initialValue = emptyList<Movie>()) { _, _, _ ->
        updateMoviesList()
    }

    private fun updateMoviesList() {
        _screenState.update {
            copy(
                movies = moviesList,
                state = MovieListScreenState.State.SUCCESS,
            )
        }
    }

    override fun onAttached() {
        viewModelScope.launch {
            try {
                moviesList = moviesInteractor.loadMoviesList()
            } catch (error: Exception) {
                moviesList = listOf()
                _screenState.update { copy(state = MovieListScreenState.State.ERROR) }
            }
        }
    }

    fun refreshData() {
        _screenState.update { copy(state = MovieListScreenState.State.LOADING) }
        viewModelScope.launch {
            try {
                moviesList = moviesInteractor.loadMoviesList()
            } catch (error: Exception) {
                moviesList = listOf()
                _screenState.update { copy(state = MovieListScreenState.State.ERROR) }
            }
        }
    }
}