package com.knowzeteam.knowze.data.remote.retrofit

import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("/api/dashboard/")
    suspend fun getDashboardData(@Header("Authorization") idToken: String): Response<DashboardResponse>
}
