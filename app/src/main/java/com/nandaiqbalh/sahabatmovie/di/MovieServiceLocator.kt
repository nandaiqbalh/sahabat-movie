package com.nandaiqbalh.sahabatmovie.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.nandaiqbalh.sahabatmovie.data.network.datasource.MovieRemoteDataSource
import com.nandaiqbalh.sahabatmovie.data.network.datasource.MovieRemoteDataSourceImpl
import com.nandaiqbalh.sahabatmovie.data.network.service.MovieApiService
import com.nandaiqbalh.sahabatmovie.data.repository.MovieRepository
import com.nandaiqbalh.sahabatmovie.data.repository.MovieRepositoryImpl

object MovieServiceLocator {

    private fun provideChucker(appContext: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(appContext).build()
    }

    private fun provideApiService(chuckerInterceptor: ChuckerInterceptor): MovieApiService {
        return MovieApiService.invoke(chuckerInterceptor)
    }

    private fun provideDataSource(apiService: MovieApiService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
    }

    fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepositoryImpl(provideDataSource(provideApiService(provideChucker(context))))
    }
}