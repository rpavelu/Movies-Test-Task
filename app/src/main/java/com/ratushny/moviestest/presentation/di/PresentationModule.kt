package com.ratushny.moviestest.presentation.di

import com.ratushny.moviestest.presentation.screen.moviedetails.MovieDetailsFragment
import com.ratushny.moviestest.presentation.screen.moviedetails.MovieDetailsViewModel
import com.ratushny.moviestest.presentation.screen.movielist.MovieListFragment
import com.ratushny.moviestest.presentation.screen.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListFragmentModule = module {
    scope<MovieListFragment> {
        viewModel {
            MovieListViewModel(moviesInteractor = get())
        }
    }
}

val movieInfoFragmentModule = module {
    scope<MovieDetailsFragment> {
        viewModel {
            MovieDetailsViewModel(moviesInteractor = get())
        }
    }
}