package com.github.cesar1287.a2mobr_tmdb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.a2mobr_tmdb.base.BaseViewModel
import com.github.cesar1287.a2mobr_tmdb.domain.HomeUseCase
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() {

    private val homeUseCase: HomeUseCase by lazy {
        HomeUseCase()
    }

    private val _nowPlayingMoviesList: MutableLiveData<List<Movie>> = MutableLiveData()

    val nowPlayingMoviesList: LiveData<List<Movie>>
        get() = _nowPlayingMoviesList


    fun getNowPlayingMovies() {
        viewModelScope.launch {
            callApi(
                call = suspend { homeUseCase.getNowPlayingMovies() },
                onSuccess = {
                    val moviesResults = it as? MoviesResults
                    _nowPlayingMoviesList.postValue(moviesResults?.results)
                }
            )

        }
    }
}