package com.github.cesar1287.a2mobr_tmdb.di

import android.content.Context
import androidx.room.Room
import com.github.cesar1287.a2mobr_tmdb.dao.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "tmdb-db"
        ).build()
    }
}