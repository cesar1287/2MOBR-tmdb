package com.github.cesar1287.a2mobr_tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.cesar1287.a2mobr_tmdb.databinding.WatchCardItemBinding
import com.github.cesar1287.a2mobr_tmdb.model.Movie

class HomeAdapter : ListAdapter<Movie, HomeAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WatchCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: WatchCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: Movie?
        ) = with(binding) {
            movie?.let {
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .into(ivWatchImage)
                tvWatchTitle.text = movie.title
                //tvWatchYear.text = movie.year
                //tvWatchGenre.text = movie.genres
                cvWatch.setOnClickListener {
                    //onItemClicked(movie)
                }
                btWatchShare.setOnClickListener {
                   // itemView.context.shareMovie(movie)
                }
            }
        }
    }
}