package com.github.cesar1287.a2mobr_tmdb.feature.home.data

import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi

interface HomeRepository {

    suspend fun getNowPlayingMovies() : ResponseApi
    fun saveMoviesFirestore(movies: List<Movie>)
    suspend fun saveMoviesRoom(movies: List<Movie>)
}