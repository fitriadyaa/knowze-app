package com.knowzeteam.knowze.di

import android.content.Context
import androidx.room.Room
import com.knowzeteam.knowze.data.local.AppDatabase
import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.remote.retrofit.ApiConfig
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import com.knowzeteam.knowze.repository.UserRepository

object ServiceLocator {

    @Volatile private var database: AppDatabase? = null

    fun provideApiService(context: Context): ApiService {
        return ApiConfig.getApiService(context)
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository.create(provideUserProfileDao(context))
    }

    private fun provideUserProfileDao(context: Context): UserProfileDao {
        return provideDatabase(context).userProfileDao()
    }

    private fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db"
            ).build().also {
                database = it
            }
        }
    }
}
