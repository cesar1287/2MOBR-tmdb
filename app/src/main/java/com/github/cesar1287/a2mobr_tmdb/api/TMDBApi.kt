package com.github.cesar1287.a2mobr_tmdb.api

import com.github.cesar1287.a2mobr_tmdb.model.MoviesResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MoviesResults>
}