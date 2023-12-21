package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class VideoRepository(private val apiService: ApiService) {

    suspend fun postVideo(idToken: String, videoRequest: VideoRequest, courseId: String): Result<VideoResponse> {
        return try {
            val response = apiService.postVideo(idToken, videoRequest, courseId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(RuntimeException("Response not successful"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getVideo(idToken: String, courseId: String): Result<VideoResponse> {
        return try {
            val response = apiService.getVideo(idToken, courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(RuntimeException("No response body found"))
            } else {
                Result.failure(RuntimeException("Response not successful: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
