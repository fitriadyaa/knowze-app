package com.knowzeteam.knowze.di

import android.content.Context
import androidx.room.Room
import com.knowzeteam.knowze.data.local.AppDatabase
import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.remote.retrofit.ApiConfig
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import com.knowzeteam.knowze.repository.GenerateRepository
import com.knowzeteam.knowze.repository.TokenRepository
import com.knowzeteam.knowze.repository.UserRepository

object ServiceLocator {

    @Volatile private var database: AppDatabase? = null

    fun provideApiService(context: Context): ApiService {
        return ApiConfig.getApiService(context)
    }

    private fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db"
            )
                // Consider adding migration strategies if you have existing users
                .fallbackToDestructiveMigration()
                .build().also {
                    database = it
                }
        }
    }

    fun provideUserRepository(context: Context): UserRepository {
        val userProfileDao = provideDatabase(context).userProfileDao()
        return UserRepository(userProfileDao)
    }

    fun provideTokenRepository(context: Context): TokenRepository {
        return TokenRepository(provideDatabase(context))
    }

    fun provideGenerateRepository(context: Context): GenerateRepository {
        return GenerateRepository(provideApiService(context))
    }
}
