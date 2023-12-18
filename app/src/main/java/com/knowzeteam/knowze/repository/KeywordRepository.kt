package com.knowzeteam.knowze.repository

import android.content.ContentValues
import android.util.Log
import com.knowzeteam.knowze.data.remote.response.keywordresponse.KeywordResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService

class KeywordRepository(private val apiService: ApiService) {
    suspend fun getKeywordTrending(): KeywordResponse? {
        val response = apiService.getKeywordTrending()
        Log.d(ContentValues.TAG, "API Response: $response")
        return if (response.isSuccessful) {
            response.body()?.let { keywordResponse ->
                keywordResponse.keywords = keywordResponse.keywords?.filterNotNull()
                keywordResponse
            }
        } else null
    }
}
