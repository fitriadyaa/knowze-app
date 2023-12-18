package com.knowzeteam.knowze.ui.screen.keyword

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.knowzeteam.knowze.repository.KeywordRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class KeywordViewModel(private val keywordRepository: KeywordRepository) : ViewModel() {
    private val _keywords = mutableStateOf<List<String>>(listOf())
    val keywords: State<List<String>> = _keywords

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

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

    fun getKeywords() {
        viewModelScope.launch {
            Log.d(ContentValues.TAG, "Fetching keywords...")
            _isLoading.value = true
            val idToken = getFirebaseAuthToken()
            if (idToken != null) {
                try {
                    Log.d(ContentValues.TAG, "Got Firebase token, fetching keywords...")
                    val response = keywordRepository.getKeywordTrending(idToken)
                    response?.keywords?.let {
                        _keywords.value = it.filterNotNull()
                        Log.d(ContentValues.TAG, "Keywords fetched: ${_keywords.value}")
                    } ?: Log.d(ContentValues.TAG, "No keywords received")
                } catch (e: Exception) {
                    Log.e(ContentValues.TAG, "Error fetching keywords: ${e.message}", e)
                } finally {
                    _isLoading.value = false
                }
            } else {
                Log.e(ContentValues.TAG, "Firebase Auth Token is null")
                _isLoading.value = false
            }
        }
    }
}