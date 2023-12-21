package com.knowzeteam.knowze.ui.screen.detailcourse

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.knowzeteam.knowze.data.remote.response.courseResponse.Content
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.SubtitlesItem
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideosItem
import com.knowzeteam.knowze.repository.GenerateRepository
import com.knowzeteam.knowze.repository.VideoRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CourseViewModel(private val generateRepository: GenerateRepository, private val videoRepository: VideoRepository) : ViewModel() {

    // LiveData to hold course details
    private val _courseDetails = MutableLiveData<CourseResponse>()
    val courseDetails: LiveData<CourseResponse> = _courseDetails

    private val _subtitleItem = MutableLiveData<SubtitlesItem>()
    val subtitlesItem: LiveData<SubtitlesItem> = _subtitleItem

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

    // LiveData to hold the list of videos
    private val _videos = MutableLiveData<VideoResponse>()
    val videos: LiveData<VideoResponse> = _videos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error


    // Function to fetch videos
    fun fetchVideos(prompt: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            val tokenResult = getFirebaseAuthToken()
            if (tokenResult != null) {
                try {
                    val videoRequest = VideoRequest(prompt)
                    val result = videoRepository.getVideos("Bearer $tokenResult", videoRequest)
                    if (result.isSuccess) {
                        result.getOrNull()?.let {
                            _videos.value = it
                        } ?: run {
                            _error.value = "No videos found"
                        }
                    } else {
                        _error.value = result.exceptionOrNull()?.message ?: "Unknown error occurred"
                    }
                } catch (e: Exception) {
                    _error.value = "Exception occurred: ${e.message}"
                } finally {
                    _isLoading.value = false
                }
            } else {
                _error.value = "Authentication token is null or empty"
                _isLoading.value = false
            }
        }
    }

    // Function to fetch course details
    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch {
            val token = getFirebaseAuthToken()
            try {
                val response = generateRepository.getCourseDetails("Bearer ${token.orEmpty()}", courseId)
                response?.let {
                    _courseDetails.value = it
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    fun getContentById(contentId: String?): Content? {
        // Assuming courseDetails is already populated with the course data
        // and each SubtitlesItem has a Content object
        return courseDetails.value?.subtitles?.firstOrNull { it?.id == contentId }?.content
    }
}