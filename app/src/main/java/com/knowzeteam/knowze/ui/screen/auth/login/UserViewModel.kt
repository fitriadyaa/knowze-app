package com.knowzeteam.knowze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowzeteam.knowze.data.local.UserProfileEntity
import com.knowzeteam.knowze.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun insertUserProfile(userProfile: UserProfileEntity) {
        viewModelScope.launch {
            userRepository.insertUserProfile(userProfile)
        }
    }

    fun getUserProfile(userId: Int, callback: (UserProfileEntity?) -> Unit) {
        viewModelScope.launch {
            val userProfile = userRepository.getUserProfile(userId)
            callback(userProfile)
        }
    }
}
