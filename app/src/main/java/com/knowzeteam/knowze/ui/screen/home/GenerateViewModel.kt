package com.knowzeteam.knowze.ui.screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateResponse
import com.knowzeteam.knowze.repository.GenerateRepository
import kotlinx.coroutines.launch

class GenerateViewModel(private val generateRepository: GenerateRepository) : ViewModel() {
    // LiveData for API response
    private val _response = MutableLiveData<GenerateResponse?>()
    val response: LiveData<GenerateResponse?> = _response

    // LiveData for Course Details
    private val _courseDetails = MutableLiveData<CourseResponse?>()
    val courseDetails: LiveData<CourseResponse?> = _courseDetails

    fun postGenerateQuery(prompt: String) {
        viewModelScope.launch {
            try {
                val result = generateRepository.postGenerateQuery(prompt)
                _response.postValue(result)
            } catch (e: Exception) {
                _response.postValue(null) // Or handle the error differently
                Log.e("GenerateViewModel", "Error: ${e.message}")
            }
        }
    }
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

}

