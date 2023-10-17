package com.github.cesar1287.a2mobr_tmdb.data

import com.github.cesar1287.a2mobr_tmdb.api.TMDBApi
import com.github.cesar1287.a2mobr_tmdb.base.BaseRepository
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApi: TMDBApi
): HomeRepository, BaseRepository() {

    override suspend fun getNowPlayingMovies(): ResponseApi {
        return safeApiCall {
            tmdbApi.getNowPlayingMovies(1)
        }.let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    response.data = response.data as? MoviesResults
                    return@let response
                }
                is ResponseApi.Error -> return@let response
            }
        }
    }
}