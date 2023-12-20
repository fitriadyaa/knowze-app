package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class VideoRepository(private val apiService: ApiService) {

    suspend fun getVideos(idToken: String, videoRequest: VideoRequest): Result<VideoResponse> {
        return try {
            val response = apiService.getVideo(idToken, videoRequest)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(RuntimeException("Response not successful"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
