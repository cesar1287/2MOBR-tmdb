package com.github.cesar1287.a2mobr_tmdb.feature.home.data

import com.github.cesar1287.a2mobr_tmdb.api.TMDBApi
import com.github.cesar1287.a2mobr_tmdb.base.BaseRepository
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import com.github.cesar1287.a2mobr_tmdb.utils.Constants.Companion.FIRESTORE_COLLECTION_MOVIES
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

    override fun saveMovies(movies: List<Movie>) {
        movies.forEach {
            Firebase.firestore.collection(
                FIRESTORE_COLLECTION_MOVIES
            ).document(it.id.toString()).set(it, SetOptions.merge())
        }
    }
}