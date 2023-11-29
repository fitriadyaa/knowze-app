package com.knowzeteam.knowze.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<String>()
    val navigationEvent: LiveData<String> = _navigationEvent

    fun navigateTo(screen: String) {
        _navigationEvent.value = screen
    }
}
