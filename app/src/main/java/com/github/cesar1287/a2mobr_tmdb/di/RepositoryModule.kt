package com.github.cesar1287.a2mobr_tmdb.di

import com.github.cesar1287.a2mobr_tmdb.api.TMDBApi
import com.github.cesar1287.a2mobr_tmdb.feature.home.data.HomeRepository
import com.github.cesar1287.a2mobr_tmdb.feature.home.data.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideHomeRepository(
        tmdbApi: TMDBApi
    ): HomeRepository {
        return HomeRepositoryImpl(tmdbApi)
    }
}