package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateRequest
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class GenerateRepository(private val apiService: ApiService) {
    suspend fun postGenerateQuery(idToken: String, prompt: String): GenerateResponse? {
        val response = apiService.postGenerate(idToken, GenerateRequest(prompt))
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getCourseDetails(idToken: String, courseId: String): CourseResponse? {
        val response = apiService.getCourseDetails(idToken, courseId)
        return if (response.isSuccessful) response.body() else null
    }
}