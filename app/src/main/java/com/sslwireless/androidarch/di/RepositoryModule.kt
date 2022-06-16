package com.sslwireless.androidarch.di

import com.sslwireless.androidarch.repo.IMoviesRepository
import com.sslwireless.androidarch.repo.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideMoviesRepo(repository: MoviesRepository): IMoviesRepository
}