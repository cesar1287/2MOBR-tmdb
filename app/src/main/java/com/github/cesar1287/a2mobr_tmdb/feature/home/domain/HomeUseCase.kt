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

    private val dateReturnedFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
    }

    private val remoteConfigDateFormat: String by lazy {
        Firebase.remoteConfig["movie_details_date_pattern"].asString()
    }

    private val actualSimpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat(remoteConfigDateFormat, Locale("pt", "BR"))
    }

    suspend fun getNowPlayingMovies(): ResponseApi {
        return when(
            val response = homeRepository.getNowPlayingMovies()
        ) {
            is ResponseApi.Success -> {
                val moviesResults = response.data as? MoviesResults
                val moviesList = moviesResults?.results
                moviesList?.map { movie ->
                    movie.posterPath = "${BuildConfig.IMAGE_URL}${movie.posterPath}"
                    movie.backdropPath = "${BuildConfig.IMAGE_URL}${movie.backdropPath}"
                    val dateReturned = dateReturnedFormat.parse(movie.releaseDate)
                    dateReturned?.let { date ->
                        movie.releaseDate = actualSimpleDateFormat.format(date)
                    }
                }

                ResponseApi.Success(moviesList)
            }
            else -> response
        }
    }

    fun saveMoviesFirestore(movies: List<Movie>) {
        homeRepository.saveMoviesFirestore(
            movies.map {
                movieNewInstance(it)
            }
        )
    }

    suspend fun saveMoviesRoom(moviesList: List<Movie>) {
        homeRepository.saveMoviesRoom(
            moviesList.map {
                movieNewInstance(it)
            }
        )
    }

    private fun movieNewInstance(it: Movie): Movie {
        val movie = it.copy()
        movie.posterPath = movie.posterPath.substringAfter(BuildConfig.IMAGE_URL)
        movie.backdropPath = movie.backdropPath.substringAfter(BuildConfig.IMAGE_URL)
        val dateReturned = actualSimpleDateFormat.parse(movie.releaseDate)
        dateReturned?.let { date ->
            movie.releaseDate = dateReturnedFormat.format(date)
        }

        return movie
    }
}