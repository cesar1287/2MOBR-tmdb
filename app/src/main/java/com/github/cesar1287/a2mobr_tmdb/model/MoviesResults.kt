package com.github.cesar1287.a2mobr_tmdb.model

import com.google.gson.annotations.SerializedName

data class MoviesResults(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)