package com.github.cesar1287.a2mobr_tmdb.feature.home.data

import com.github.cesar1287.a2mobr_tmdb.api.TMDBApi
import com.github.cesar1287.a2mobr_tmdb.base.BaseRepository
import com.github.cesar1287.a2mobr_tmdb.dao.AppDatabase
import com.github.cesar1287.a2mobr_tmdb.model.Movie
import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import com.github.cesar1287.a2mobr_tmdb.utils.Constants.Companion.FIRESTORE_COLLECTION_MOVIES
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApi: TMDBApi,
    private val appDatabase: AppDatabase
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
                is ResponseApi.Error -> {
                    val moviesRef = Firebase.firestore.collection(FIRESTORE_COLLECTION_MOVIES)
                    return try {
                        val querySnapshot = moviesRef.get().await()
                        ResponseApi.Success(
                            MoviesResults(
                                results = querySnapshot.toObjects(Movie::class.java)
                            )
                        )
                    } catch (exception: Exception) {
                        Firebase.crashlytics.recordException(exception)
                        response
                    }
                }
            }
        }
    }

    override fun saveMoviesFirestore(movies: List<Movie>) {
        movies.forEach {
            Firebase.firestore.collection(
                FIRESTORE_COLLECTION_MOVIES
            ).document(it.id.toString()).set(it, SetOptions.merge())
        }
    }

    override suspend fun saveMoviesRoom(movies: List<Movie>) {
        appDatabase.movieDao().insertAll(movies)
    }
}