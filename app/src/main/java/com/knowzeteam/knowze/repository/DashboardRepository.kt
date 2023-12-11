package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.authResponse.DashboardResponse

interface DashboardRepository {
    suspend fun getDashboard(): Result<DashboardResponse>
}
