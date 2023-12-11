package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import retrofit2.Response

class DashboardRepository(private val apiService: ApiService) {
    suspend fun getDashboardData(idToken: String): Response<DashboardResponse> {
        return apiService.getDashboardData(idToken)
    }
}
