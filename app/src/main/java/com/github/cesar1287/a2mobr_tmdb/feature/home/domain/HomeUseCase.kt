package com.github.cesar1287.a2mobr_tmdb.feature.home.domain

import com.github.cesar1287.a2mobr_tmdb.BuildConfig
import com.github.cesar1287.a2mobr_tmdb.feature.home.data.HomeRepository
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import java.text.SimpleDateFormat
import java.util.Locale
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
                    val dateReturnedFormat =
                        SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
                    val dateReturned = dateReturnedFormat.parse(it.releaseDate)
                    val remoteConfigDateFormat = Firebase.remoteConfig["movie_details_date_pattern"].asString()
                    val actualSimpleDateFormat =
                        SimpleDateFormat(remoteConfigDateFormat, Locale("pt", "BR"))
                    it.releaseDate = actualSimpleDateFormat.format(dateReturned)
                }

                ResponseApi.Success(moviesList)
            }
            else -> response
        }
    }

    fun saveMovies(movies: List<Movie>) {
        homeRepository.saveMoviesFirestore(
            movies.map {
                val movie = it.copy()
                movie.posterPath = movie.posterPath.substringAfter(BuildConfig.IMAGE_URL)
                movie.backdropPath = movie.backdropPath.substringAfter(BuildConfig.IMAGE_URL)

                movie
            }
        )
    }

    suspend fun saveMoviesRoom(moviesList: List<Movie>) {
        homeRepository.saveMoviesRoom(
            moviesList.map {
                val movie = it.copy()
                movie.posterPath = movie.posterPath.substringAfter(BuildConfig.IMAGE_URL)
                movie.backdropPath = movie.backdropPath.substringAfter(BuildConfig.IMAGE_URL)

                movie
            }
        )
    }
}