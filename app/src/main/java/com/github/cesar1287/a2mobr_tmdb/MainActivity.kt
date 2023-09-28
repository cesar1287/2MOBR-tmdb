package com.github.cesar1287.a2mobr_tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.a2mobr_tmdb.adapter.HomeAdapter
import com.github.cesar1287.a2mobr_tmdb.databinding.ActivityMainBinding
import com.github.cesar1287.a2mobr_tmdb.presentation.HomeViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.command = MutableLiveData()
        viewModel.getNowPlayingMovies()

        registerObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNowPlayingMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeAdapter
        }
    }

    private fun registerObservers() {
        viewModel.nowPlayingMoviesList.observe(this) {
            it?.let {
                homeAdapter.submitList(it)
            }
        }
    }
}