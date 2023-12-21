package com.knowzeteam.knowze.ui.screen.gallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.knowzeteam.knowze.data.remote.response.courseResponse.ResultsItem
import com.knowzeteam.knowze.repository.AllCourseRepository

class CoursePagingSource(
    private val repository: AllCourseRepository
) : PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val currentPage = params.key ?: 1
        return try {
            val response = repository.getAllCourses(currentPage, params.loadSize)
            // Filtering out any null items from the list
            val data = response.body()?.results?.filterNotNull() ?: emptyList()
            val nextKey = if (data.isEmpty()) null else currentPage + 1
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        // Implement this to define how to get the key for the initial load
        return state.anchorPosition
    }
}
