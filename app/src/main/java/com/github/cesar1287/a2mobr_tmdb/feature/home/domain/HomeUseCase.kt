package com.github.cesar1287.a2mobr_tmdb.feature.home.domain

import com.github.cesar1287.a2mobr_tmdb.BuildConfig
import com.github.cesar1287.a2mobr_tmdb.feature.home.data.HomeRepository
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getNowPlayingMovies(): ResponseApi {
        return when(
            val response = homeRepository.getNowPlayingMovies()
        ) {
            is ResponseApi.Success -> {
                val moviesResults = response.data as? MoviesResults
                val moviesList = moviesResults?.results
                moviesList?.map {
                    it.posterPath = "${BuildConfig.IMAGE_URL}${it.posterPath}"
                    it.backdropPath = "${BuildConfig.IMAGE_URL}${it.backdropPath}"
                }

                ResponseApi.Success(moviesList)
            }
            else -> response
        }
    }

    fun saveMovies(movies: List<Movie>) {
        homeRepository.saveMovies(
            movies.map {
                val movie = it.copy()
                movie.posterPath = movie.posterPath.substringAfter(BuildConfig.IMAGE_URL)
                movie.backdropPath = movie.backdropPath.substringAfter(BuildConfig.IMAGE_URL)

                movie
            }
        )
    }
}