package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.response.newsresponse.NewsResponseItem
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class NewsRepository(private val apiService: ApiService) {
    suspend fun getTrendingNews(idToken: String): List<NewsResponseItem> {
        val response = apiService.getNews(idToken)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching news: ${response.errorBody()?.string()}")
        }
    }
}
