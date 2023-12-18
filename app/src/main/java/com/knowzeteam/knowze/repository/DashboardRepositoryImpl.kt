package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.authResponse.DashboardResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class DashboardRepositoryImpl(private val apiService: ApiService) : DashboardRepository {
    override suspend fun getDashboard(): Result<DashboardResponse> {
        return try {
            val response = apiService.getDashboard()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(RuntimeException("Failed to get dashboard data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
