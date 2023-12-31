package com.github.cesar1287.a2mobr_tmdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.cesar1287.a2mobr_tmdb.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>
}