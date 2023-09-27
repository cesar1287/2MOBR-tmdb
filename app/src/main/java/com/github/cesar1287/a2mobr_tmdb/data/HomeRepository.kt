package com.github.cesar1287.a2mobr_tmdb.data

interface HomeRepository {

    suspend fun getNowPlayingMovies()
}