package com.knowzeteam.knowze.ui.screen.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.knowzeteam.knowze.data.remote.response.newsresponse.NewsResponseItem
import com.knowzeteam.knowze.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsItems = MutableStateFlow<List<NewsResponseItem>>(emptyList())
    val newsItems: StateFlow<List<NewsResponseItem>> = _newsItems
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private suspend fun getFirebaseAuthToken(): String? {
        return try {
            val mUser = firebaseAuth.currentUser
            if (mUser != null) {
                // User is authenticated, proceed to get the token
                val tokenResult = mUser.getIdToken(true).await()
                val token = tokenResult.token
                if (token != null) {
                    // Log the token when it's successfully retrieved
                    Log.d(ContentValues.TAG, "Firebase token: $token")
                    token
                } else {
                    val errorMessage = "Firebase token is null"
                    Log.e(ContentValues.TAG, errorMessage)
                    null
                }
            } else {
                val errorMessage = "User is not authenticated with Firebase"
                Log.e(ContentValues.TAG, errorMessage)
                null
            }
        } catch (e: Exception) {
            val errorMessage = "Error getting Firebase token: ${e.message ?: "Unknown error"}"
            Log.e(ContentValues.TAG, errorMessage, e)
            null
        }
    }

    fun fetchNews() {
        viewModelScope.launch {
            val token = getFirebaseAuthToken()
            Log.d(ContentValues.TAG, "Fetching news with token: $token")
            try {
                val newsList = newsRepository.getTrendingNews("Bearer $token")
                _newsItems.value = newsList
                Log.d(ContentValues.TAG, "News items loaded: ${_newsItems.value.size}")
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Exception in fetching news", e)
            }
        }
    }
}