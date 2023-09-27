package com.github.cesar1287.a2mobr_tmdb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.a2mobr_tmdb.domain.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val homeUseCase: HomeUseCase by lazy {
        HomeUseCase()
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            homeUseCase.getNowPlayingMovies()
        }
    }
}