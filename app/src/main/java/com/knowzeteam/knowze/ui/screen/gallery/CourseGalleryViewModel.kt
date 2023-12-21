package com.knowzeteam.knowze.ui.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knowzeteam.knowze.data.remote.response.courseResponse.ResultsItem
import com.knowzeteam.knowze.repository.AllCourseRepository
import kotlinx.coroutines.flow.Flow

class CourseGalleryViewModel(private val repository: AllCourseRepository) : ViewModel() {
    val allCourses: Flow<PagingData<ResultsItem>> = Pager(
        config = PagingConfig(pageSize = 5, enablePlaceholders = false),
        pagingSourceFactory = { CoursePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)
}