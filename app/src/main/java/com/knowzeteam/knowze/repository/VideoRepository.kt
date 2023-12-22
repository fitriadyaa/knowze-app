package com.knowzeteam.knowze.repository

import android.util.Log
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class VideoRepository(private val apiService: ApiService) {

    suspend fun postVideo(idToken: String, videoRequest: VideoRequest, courseId: String) {
        try {
            val response = apiService.postVideo(idToken, videoRequest, courseId)
            if (response.isSuccessful) {
                Log.d("VideoRepository", "Video posted successfully")
            } else {
                Log.e("VideoRepository", "Video post failed with code ${response.code()}")
            }
        } catch (e: Exception) {
           Log.e("VideoRepository", "Error posting video: ${e.message}")
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
