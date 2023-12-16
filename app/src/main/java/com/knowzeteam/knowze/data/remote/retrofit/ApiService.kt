package com.knowzeteam.knowze.data.remote.retrofit

import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateRequest
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateResponse
import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/api/dashboard/")
    suspend fun getDashboardData(@Header("Authorization") idToken: String): Response<DashboardResponse>

    @POST("/api/generate/")
    suspend fun postGenerate(@Body generateRequest: GenerateRequest): Response<GenerateResponse>

    @GET("/api/course/{course_id}")
    suspend fun getCourseDetails(@Path("course_id") courseId: String): Response<CourseResponse>
}
