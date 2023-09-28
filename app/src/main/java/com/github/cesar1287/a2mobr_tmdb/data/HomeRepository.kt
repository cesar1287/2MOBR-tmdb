package com.github.cesar1287.a2mobr_tmdb.data

import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi

interface HomeRepository {

    suspend fun getNowPlayingMovies() : ResponseApi
}