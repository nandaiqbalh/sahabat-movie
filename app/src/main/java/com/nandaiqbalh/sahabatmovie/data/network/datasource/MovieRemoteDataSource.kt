package com.nandaiqbalh.sahabatmovie.data.network.datasource

import com.nandaiqbalh.sahabatmovie.data.network.model.detail.DetailMovie
import com.nandaiqbalh.sahabatmovie.data.network.model.popular.Popular
import com.nandaiqbalh.sahabatmovie.data.network.model.search.Search
import com.nandaiqbalh.sahabatmovie.data.network.model.toprated.TopRated
import com.nandaiqbalh.sahabatmovie.data.network.model.upcoming.Upcoming
import com.nandaiqbalh.sahabatmovie.data.network.service.MovieApiService

interface MovieRemoteDataSource {
    suspend fun getPopular(): Popular
    suspend fun getTopRated(): TopRated
    suspend fun getUpcoming(): Upcoming
    suspend fun searchMovie(query: String): Search
    suspend fun getDetail(id: Int): DetailMovie
}

class MovieRemoteDataSourceImpl(private val apiService: MovieApiService): MovieRemoteDataSource {
    override suspend fun getPopular(): Popular {
        return apiService.getPopular()
    }

    override suspend fun getTopRated(): TopRated {
        return apiService.getTopRated()
    }

    override suspend fun getUpcoming(): Upcoming {
        return apiService.getUpcoming()
    }

    override suspend fun searchMovie(query: String): Search {
        return apiService.searchMovie(query = query)
    }

    override suspend fun getDetail(id: Int): DetailMovie {
        return apiService.getDetail(id)
    }

}