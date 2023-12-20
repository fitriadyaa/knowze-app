package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import retrofit2.Response

class RecommendationRepository(private val apiService: ApiService) {

    suspend fun getRecommendations(idToken: String): Response<List<String?>> {
        // Call the getRecommendation endpoint
        val response = apiService.getRecommendation(idToken)
        return if (response.isSuccessful) {
            // Extract the list of recommendations from the response
            Response.success(response.body()?.recommendations)
        } else {
            throw Exception("Error fetching news: ${response.errorBody()?.string()}")
        }
    }
}
