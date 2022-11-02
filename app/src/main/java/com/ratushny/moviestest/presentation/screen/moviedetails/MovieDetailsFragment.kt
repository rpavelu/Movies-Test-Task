package com.ratushny.moviestest.presentation.screen.moviedetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ratushny.moviestest.databinding.FragmentMovieDetailsBinding
import com.ratushny.moviestest.presentation.screen.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment :
    BaseFragment<MovieDetailsScreenState, FragmentMovieDetailsBinding, MovieDetailsViewModel>() {

    override val viewModel: MovieDetailsViewModel by viewModel()

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieDetailsBinding =
        FragmentMovieDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadMovieDetails(args.movieId)
    }

    override fun screenStateObserver(): Observer<MovieDetailsScreenState> = Observer { state ->
        with(binding) {
            progressLoading.isVisible = state.isLoading

            val isFailure = state.movie.isFailure
            textError.isVisible = isFailure

            val movie = state.movie.getOrNull()

            textTitle.text = movie?.title
            textTitleOriginal.text = movie?.originalTitle
            textTagline.text = movie?.tagline
            textDescription.text = movie?.overview

            if (state.movie.isSuccess && movie?.posterPath?.isNotEmpty() == true) {
                Glide.with(requireContext())
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
                    .into(imagePoster)
            }
        }
    }
}