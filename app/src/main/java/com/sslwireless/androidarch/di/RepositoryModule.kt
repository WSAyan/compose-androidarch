package com.sslwireless.androidarch.di

import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.repo.donors.DonorsRepository
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