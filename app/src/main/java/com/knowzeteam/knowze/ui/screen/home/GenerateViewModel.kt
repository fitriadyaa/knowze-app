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
import com.knowzeteam.knowze.repository.GenerateRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GenerateViewModel(private val generateRepository: GenerateRepository) : ViewModel() {
    // LiveData for API response
    private val _response = MutableLiveData<GenerateResponse?>()
    val response: LiveData<GenerateResponse?> = _response
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    // LiveData for Course Details
    private val _courseDetails = MutableLiveData<CourseResponse?>()
    val courseDetails: LiveData<CourseResponse?> = _courseDetails

    private fun getCourseDetails(courseId: String) {
        viewModelScope.launch {
            try {
                val courseResult = generateRepository.getCourseDetails(courseId)
                _courseDetails.postValue(courseResult)
            } catch (e: Exception) {
                _courseDetails.postValue(null)
                Log.e("GenerateViewModel", "Error fetching course details: ${e.message}")
            }
        }
    }

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
            try {
                val result = generateRepository.postGenerateQuery("Bearer ${token.orEmpty()}", prompt)
                _response.postValue(result)
            } catch (e: Exception) {
                _response.postValue(null) // Or handle the error differently
                Log.e("GenerateViewModel", "Error: ${e.message}")
            }
        }
    }

}
