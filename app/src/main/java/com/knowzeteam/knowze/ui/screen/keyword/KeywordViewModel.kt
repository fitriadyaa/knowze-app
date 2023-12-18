package com.knowzeteam.knowze.ui.screen.keyword

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowzeteam.knowze.repository.KeywordRepository
import kotlinx.coroutines.launch

class KeywordViewModel(private val keywordRepository: KeywordRepository) : ViewModel() {
    private val _keywords = mutableStateOf<List<String>>(listOf())
    val keywords: State<List<String>> = _keywords

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getKeywords() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = keywordRepository.getKeywordTrending()
            response?.keywords?.let {
                _keywords.value = it.filterNotNull()
                Log.d(ContentValues.TAG, "Keywords fetched: ${_keywords.value}")
            } ?: Log.d(ContentValues.TAG, "No keywords received")
            _isLoading.value = false
        }
    }
}