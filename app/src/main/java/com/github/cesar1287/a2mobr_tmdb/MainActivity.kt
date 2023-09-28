package com.github.cesar1287.a2mobr_tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.cesar1287.a2mobr_tmdb.databinding.ActivityMainBinding
import com.github.cesar1287.a2mobr_tmdb.presentation.HomeViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getNowPlayingMovies()

        registerObservers()
    }

    private fun registerObservers() {
        viewModel.nowPlayingMoviesList.observe(this) {

        }
    }
}