package com.github.cesar1287.a2mobr_tmdb.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.github.cesar1287.a2mobr_tmdb.databinding.CmpMediaDetailsBottomPosterCardBinding
import com.github.cesar1287.a2mobr_tmdb.databinding.FragmentMovieDetailsBinding
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.utils.shareMovie
import com.github.cesar1287.a2mobr_tmdb.utils.showNearbyTheaters

class MovieDetailsFragment : Fragment() {

    private var binding: FragmentMovieDetailsBinding? = null

    private val movieDetailsFragmentArgs: MovieDetailsFragmentArgs by navArgs()
    private val movie: Movie by lazy {
        movieDetailsFragmentArgs.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {
                setupHeader(movie, it.cmpMovieDetailsBottomCard)

                btMovieDetailsBackIcon.setOnClickListener {
                    activity?.onBackPressed()
                }

                activity?.let { activityNonNull ->
                    Glide.with(activityNonNull).load(movie.backdropPath)
                        .into(ivMovieDetailsPosterImage)
                }

                tvMovieDetailsDescriptionText.text = movie.overview

                btMovieDetailsShareButton.setOnClickListener {
                    movie.let { activity?.shareMovie(movie) }
                }

                btMovieDetailNearbyTheaters.setOnClickListener {
                    activity?.showNearbyTheaters()
                }
            }
        }
    }

    private fun setupHeader(
        movieItem: Movie,
        cmpMovieDetailsBottomCard: CmpMediaDetailsBottomPosterCardBinding
    ) {
        with(cmpMovieDetailsBottomCard) {
            tvCmpMediaDetailsTitle.text = movieItem.title
            tvCmpMediaDetailsYear.text = movieItem.releaseDate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}