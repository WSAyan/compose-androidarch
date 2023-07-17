package com.wsayan.androidarch.di

import com.wsayan.androidarch.repo.donors.IDonorsRepository
import com.wsayan.androidarch.repo.donors.DonorsRepository
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
    fun provideMoviesRepo(repository: DonorsRepository): IDonorsRepository
}