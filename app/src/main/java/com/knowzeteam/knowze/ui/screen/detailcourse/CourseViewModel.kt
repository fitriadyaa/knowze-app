package com.knowzeteam.knowze.ui.screen.detailcourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.repository.GenerateRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val generateRepository: GenerateRepository) : ViewModel() {

    // LiveData to hold course details
    private val _courseDetails = MutableLiveData<CourseResponse>()
    val courseDetails: LiveData<CourseResponse> = _courseDetails

    // Function to fetch course details
    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch {
            try {
                val response = generateRepository.getCourseDetails(courseId)
                response?.let {
                    _courseDetails.postValue(it)
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}