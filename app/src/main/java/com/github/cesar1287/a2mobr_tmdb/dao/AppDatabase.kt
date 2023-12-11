package com.github.cesar1287.a2mobr_tmdb.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.cesar1287.a2mobr_tmdb.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}