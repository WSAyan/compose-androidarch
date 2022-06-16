package com.sslwireless.androidarch.repo

import com.sslwireless.androidarch.db.entity.ImagesConfig
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.data.ConfigurationResponse
import com.sslwireless.androidarch.network.data.Images
import com.sslwireless.androidarch.network.data.MovieDetailsResponse
import com.sslwireless.androidarch.network.data.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun fetchPopularMovies(): MovieListResponse
    suspend fun fetchPopularMovies(page: Int): MovieListResponse
    suspend fun fetchMovieDetails(id: Int): MovieDetailsResponse
    suspend fun fetchConfigurations(): ConfigurationResponse
    suspend fun insertImageConfig(images: Images)
    fun selectImageConfig(): Flow<ImagesConfig>
    fun isEmptyImageConfig(): Flow<Boolean>
    fun cacheImageBaseUrl(url: String)
    val imageBaseUrl: String
    suspend fun fetchConfigurations2(): Flow<NetworkState<ConfigurationResponse>>
}