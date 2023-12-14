package com.knowzeteam.knowze.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import com.knowzeteam.knowze.di.ServiceLocator
import com.knowzeteam.knowze.ui.screen.auth.login.LoginViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            // Provide ApiService
            val apiService = ServiceLocator.provideApiService(context)

            // Provide UserRepository
            val userRepository = ServiceLocator.provideUserRepository(context)

            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(apiService, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
