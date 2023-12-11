package com.knowzeteam.knowze.data.remote.retrofit

import com.knowzeteam.knowze.data.remote.response.authResponse.DashboardResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api/dashboard/")
    suspend fun getDashboard(): Response<DashboardResponse>
}
