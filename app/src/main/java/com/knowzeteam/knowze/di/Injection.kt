package com.knowzeteam.knowze.di

import android.content.Context
import com.knowzeteam.knowze.data.remote.retrofit.ApiConfig
import com.knowzeteam.knowze.repository.DashboardRepository
import com.knowzeteam.knowze.repository.DashboardRepositoryImpl

object Injection {
    fun provideRepository(context: Context): DashboardRepository {
        return DashboardRepositoryImpl(ApiConfig.getApiService(context))
    }
}
