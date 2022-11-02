package com.ratushny.moviestest.presentation.screen.movielist

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ratushny.moviestest.databinding.ItemMovieBinding
import com.ratushny.moviestest.domain.model.Movie

class MovieListAdapter(
    private val onClick: (movie: Movie) -> Unit,
) : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    inner class ViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemMovieBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: Movie,
            onClick: (movie: Movie) -> Unit
        ) = with(binding) {
            textMovieName.text = movie.title
            textMovieYear.text = movie.releaseDate.year.toString()
            textMovieRating.text = movie.voteAverage.toString()

            Glide.with(itemView)
                .load(movie.posterPath)
                .fitCenter()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageProgressbar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageProgressbar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageMovie)

            itemView.setOnClickListener { onClick(movie) }
        }
    }
}