package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.courseResponse.AllCourseResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import retrofit2.Response

class AllCourseRepository(private val apiService: ApiService) {
    suspend fun getAllCourses(page: Int, pageSize: Int): Response<AllCourseResponse> {
        return apiService.getAllCourse(page, pageSize)
    }
}