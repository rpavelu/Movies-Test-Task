package com.ratushny.moviestest.presentation.screen.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.ratushny.moviestest.R
import com.ratushny.moviestest.databinding.FragmentMovieListBinding
import com.ratushny.moviestest.presentation.screen.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment :
    BaseFragment<MovieListScreenState, FragmentMovieListBinding, MovieListViewModel>() {

    override val viewModel: MovieListViewModel by viewModel()

    private val moviesAdapter: MovieListAdapter by lazy {
        MovieListAdapter { movie ->
            navigation.navigate(MovieListFragmentDirections.openMovieFragment(movie.id))
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListBinding = FragmentMovieListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbarMenu()
        binding.recyclerviewMovies.apply {
            adapter = moviesAdapter
        }
    }

    private fun initToolbarMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
                menuInflater.inflate(R.menu.menu_movie_list, menu)

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_refresh -> viewModel.refreshData()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun screenStateObserver(): Observer<MovieListScreenState> = Observer { state ->
        with(binding) {
            progressLoading.isVisible = state.state == MovieListScreenState.State.LOADING
            textError.isVisible = state.state == MovieListScreenState.State.ERROR
            textEmpty.isVisible = state.state == MovieListScreenState.State.SUCCESS
                    && state.movies.isEmpty()
            recyclerviewMovies.isVisible = state.state == MovieListScreenState.State.SUCCESS
                    && state.movies.isNotEmpty()

            moviesAdapter.submitList(state.movies)
        }
    }
}