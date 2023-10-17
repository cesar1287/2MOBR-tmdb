package com.github.cesar1287.a2mobr_tmdb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.a2mobr_tmdb.base.BaseViewModel
import com.github.cesar1287.a2mobr_tmdb.domain.HomeUseCase
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
)  : BaseViewModel() {

    private val _nowPlayingMoviesList: MutableLiveData<List<Movie>> = MutableLiveData()

    val nowPlayingMoviesList: LiveData<List<Movie>>
        get() = _nowPlayingMoviesList


    fun getNowPlayingMovies() {
        viewModelScope.launch {
            callApi(
                call = suspend { homeUseCase.getNowPlayingMovies() },
                onSuccess = {
                    val genericList = it as? List<*>
                    _nowPlayingMoviesList.postValue(genericList?.filterIsInstance<Movie>())
                }
            )

        }
    }
}