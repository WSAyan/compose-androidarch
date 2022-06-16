package com.sslwireless.androidarch.repo


import com.sslwireless.androidarch.BuildConfig
import com.sslwireless.androidarch.db.RoomHelper
import com.sslwireless.androidarch.db.entity.ImagesConfig
import com.sslwireless.androidarch.network.IApiService
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.convertData
import com.sslwireless.androidarch.network.data.ConfigurationResponse
import com.sslwireless.androidarch.network.data.Images
import com.sslwireless.androidarch.network.data.MovieDetailsResponse
import com.sslwireless.androidarch.network.data.MovieListResponse
import com.sslwireless.androidarch.preference.PreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    val apiService: IApiService,
    val preferencesHelper: PreferencesHelper,
    val roomHelper: RoomHelper
) : IMoviesRepository, BaseRepository() {

    override suspend fun fetchPopularMovies(): MovieListResponse {
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY
        return apiService
            .getRequest("3/movie/popular", hashMap)
            .convertData(MovieListResponse::class) as MovieListResponse
    }

    override suspend fun fetchPopularMovies(page: Int): MovieListResponse {
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY
        hashMap["page"] = page.toString()
        return apiService
            .getRequest("3/movie/popular", hashMap)
            .convertData(MovieListResponse::class) as MovieListResponse
    }

    override suspend fun fetchMovieDetails(id: Int): MovieDetailsResponse {
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY
        return apiService
            .getRequest("3/movie/$id", hashMap)
            .convertData(MovieDetailsResponse::class) as MovieDetailsResponse
    }

    override suspend fun fetchConfigurations(): ConfigurationResponse {
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY
        return apiService
            .getRequest("3/configuration", hashMap)
            .convertData(ConfigurationResponse::class) as ConfigurationResponse
    }

    override suspend fun fetchConfigurations2(): Flow<NetworkState<ConfigurationResponse>> {
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY

        return handleNetworkCall {
            apiService
                .getRequest("3/configuration", hashMap)
                .convertData(ConfigurationResponse::class) as ConfigurationResponse
        }
    }

    override suspend fun insertImageConfig(images: Images) {
        roomHelper
            .getDatabase()
            .imageConfigDao()
            .insert(
                ImagesConfig(
                    base_url = images.base_url,
                    secure_base_url = images.secure_base_url,
                    backdrop_sizes = images.backdrop_sizes?.joinToString(separator = ","),
                    logo_sizes = images.logo_sizes?.joinToString(separator = ","),
                    poster_sizes = images.poster_sizes?.joinToString(separator = ","),
                    profile_sizes = images.profile_sizes?.joinToString(separator = ","),
                    still_sizes = images.still_sizes?.joinToString(separator = ",")
                )
            )
    }

    override fun selectImageConfig(): Flow<ImagesConfig> =
        roomHelper
            .getDatabase()
            .imageConfigDao()
            .getLatestConfig()

    override fun isEmptyImageConfig(): Flow<Boolean> =
        roomHelper
            .getDatabase()
            .imageConfigDao()
            .isEmptyConfig()

    override fun cacheImageBaseUrl(url: String) =
        preferencesHelper.put("image_base", url)

    override val imageBaseUrl = preferencesHelper["image_base", ""]

}