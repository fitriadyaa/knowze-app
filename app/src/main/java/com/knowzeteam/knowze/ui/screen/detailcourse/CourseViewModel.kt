package com.knowzeteam.knowze.ui.screen.detailcourse

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.repository.GenerateRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CourseViewModel(private val generateRepository: GenerateRepository) : ViewModel() {

    // LiveData to hold course details
    private val _courseDetails = MutableLiveData<CourseResponse>()
    val courseDetails: LiveData<CourseResponse> = _courseDetails
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

    // Function to fetch course details
    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch {
            val token = getFirebaseAuthToken()
            try {
                val response = generateRepository.getCourseDetails("Bearer ${token.orEmpty()}", courseId)
                response?.let {
                    _courseDetails.postValue(it)
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}