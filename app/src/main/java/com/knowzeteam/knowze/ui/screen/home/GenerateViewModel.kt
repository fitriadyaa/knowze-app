package com.knowzeteam.knowze.ui.screen.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateResponse
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideosItem
import com.knowzeteam.knowze.repository.GenerateRepository
import com.knowzeteam.knowze.repository.RecommendationRepository
import com.knowzeteam.knowze.repository.VideoRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.net.SocketTimeoutException

class GenerateViewModel(
    private val generateRepository: GenerateRepository,
    private val recommendationRepository: RecommendationRepository,
    private val videoRepository: VideoRepository
) : ViewModel() {
    // LiveData for API response
    private val _response = MutableLiveData<GenerateResponse?>()
    val response: LiveData<GenerateResponse?> = _response
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

    fun postGenerateQuery(prompt: String) {
        viewModelScope.launch {
            val token = getFirebaseAuthToken()
            if (token.isNullOrEmpty()) {
                Log.e("GenerateViewModel", "Firebase token is empty or null")
                _response.value = null
                return@launch
            }
            try {
                val result = generateRepository.postGenerateQuery("Bearer $token", prompt)
                _response.value = result
            } catch (e: SocketTimeoutException) {
                Log.e("GenerateViewModel", "Network request timed out: ${e.message}")
                _response.value = null// Handle the timeout specifically
            } catch (e: Exception) {
                Log.e("GenerateViewModel", "Error: ${e.message}")
                _response.value = null // Handle other errors
            }
        }
    }

    private val _recommendations = MutableLiveData<List<String?>>()
    val recommendations: LiveData<List<String?>> = _recommendations

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error

    fun fetchRecommendations() {
        viewModelScope.launch {
            val token = getFirebaseAuthToken()
            try {
                _isLoading.value = true
                val response = recommendationRepository.getRecommendations("Bearer $token")
                if (response.isSuccessful) {
                    _recommendations.value = response.body()
                } else {
                    // Handle error response
                    _error.value =
                        "Error fetching recommendations: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                // Handle exceptions like network errors
                _error.value = "Exception occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onSearch(prompt: String) {
        viewModelScope.launch {
            // Posting the generate query
            postGenerateQuery(prompt)

            // Fetching videos
//            fetchVideos(prompt)
        }
    }
}
