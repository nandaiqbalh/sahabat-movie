package com.nandaiqbalh.sahabatmovie.di

import android.content.Context
import com.nandaiqbalh.sahabatmovie.data.local.database.AppDatabase
import com.nandaiqbalh.sahabatmovie.data.local.database.user.UserDao
import com.nandaiqbalh.sahabatmovie.data.local.database.user.UserLocalDataSource
import com.nandaiqbalh.sahabatmovie.data.local.database.user.UserLocalDataSourceImpl
import com.nandaiqbalh.sahabatmovie.data.local.preference.UserPreference
import com.nandaiqbalh.sahabatmovie.data.local.preference.UserPreferenceDataSource
import com.nandaiqbalh.sahabatmovie.data.local.preference.UserPreferenceDataSourceImpl
import com.nandaiqbalh.sahabatmovie.data.repository.UserRepository
import com.nandaiqbalh.sahabatmovie.data.repository.UserRepositoryImpl

object UserServiceLocator {

    private fun provideUserPreference(context: Context): UserPreference {
        return UserPreference(context)
    }

    private fun provideUserPreferenceDataSource(context: Context): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(provideUserPreference(context))
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    private fun provideUserDao(context: Context): UserDao {
        return provideAppDatabase(context).userDao()
    }

    private fun provideUserDataSource(context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(provideUserDao(context))
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryImpl(
            provideUserPreferenceDataSource(context),
            provideUserDataSource(context)
        )
    }
}