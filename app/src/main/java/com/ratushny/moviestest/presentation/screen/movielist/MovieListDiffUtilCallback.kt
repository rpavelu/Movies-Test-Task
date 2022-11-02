package com.ratushny.moviestest.presentation.screen.movielist

import androidx.recyclerview.widget.DiffUtil
import com.ratushny.moviestest.domain.model.Movie

class MovieListDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}