package com.github.cesar1287.a2mobr_tmdb.domain

import com.github.cesar1287.a2mobr_tmdb.data.HomeRepository
import com.github.cesar1287.a2mobr_tmdb.data.HomeRepositoryImpl
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi

class HomeUseCase {

    private val homeRepository: HomeRepository by lazy {
        HomeRepositoryImpl()
    }

    suspend fun getNowPlayingMovies(): ResponseApi {
        return homeRepository.getNowPlayingMovies()
    }
}