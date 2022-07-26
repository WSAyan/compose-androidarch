package com.sslwireless.androidarch.repo.donors


import androidx.paging.*
import com.sslwireless.androidarch.db.RoomHelper
import com.sslwireless.androidarch.network.IApiService
import com.sslwireless.androidarch.network.NetworkErrorExceptions
import com.sslwireless.androidarch.network.AppNetworkState
import com.sslwireless.androidarch.network.convertData
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.network.data.donors.DonorsResponse
import com.sslwireless.androidarch.network.data.resources.ResourcesResponse
import com.sslwireless.androidarch.preference.PreferencesHelper
import com.sslwireless.androidarch.repo.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DonorsRepository @Inject constructor(
    override var apiService: IApiService,
    override var preferencesHelper: PreferencesHelper,
    override var roomHelper: RoomHelper
) : IDonorsRepository, BaseRepository() {
    override suspend fun fetchResources(): Flow<AppNetworkState<ResourcesResponse>> {
        val hashMap = HashMap<String, String>()

        return handleNetworkCall {
            apiService
                .getRequest("blood-finder-resources", hashMap)
                .convertData(ResourcesResponse::class) as ResourcesResponse
        }

    }

    override suspend fun fetchBloodDonors(page: Int, user_name: String): DonorsResponse {
        val hashMap = HashMap<String, String>()
        hashMap["page"] = page.toString()
        hashMap["user_name"] = user_name

        return apiService
            .getRequest("blood-finder", hashMap)
            .convertData(DonorsResponse::class) as DonorsResponse
    }


    override fun fetchPaginatedDonorsList(user_name: String): Flow<PagingData<Donor>> {
        return Pager(PagingConfig(pageSize = 20)) {
            DonorSource(this, user_name)
        }.flow
    }

    class DonorSource(var moviesRepo: IDonorsRepository, var user_name: String) :
        PagingSource<Int, Donor>() {

        override fun getRefreshKey(state: PagingState<Int, Donor>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Donor> {
            return try {
                val nextPage = params.key ?: 1
                val donorsResponse =
                    moviesRepo.fetchBloodDonors(page = nextPage, user_name = user_name)

                if (donorsResponse.code != 200) throw NetworkErrorExceptions(
                    errorMessage = donorsResponse.message,
                    unauthorized = donorsResponse.code == 401
                )

                val list = donorsResponse.data?.data ?: throw NetworkErrorExceptions(
                    errorMessage = donorsResponse.message
                )

                LoadResult.Page(
                    data = list,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (list.isEmpty()) null else nextPage.plus(1)
                )
            } catch (exception: NetworkErrorExceptions) {
                exception.printStackTrace()
                return LoadResult.Error(exception)
            } catch (exception: Exception) {
                exception.printStackTrace()
                return LoadResult.Error(NetworkErrorExceptions(errorMessage = exception.message))
            }
        }
    }

}