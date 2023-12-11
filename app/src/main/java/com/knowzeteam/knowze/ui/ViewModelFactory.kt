package com.knowzeteam.knowze.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knowzeteam.knowze.repository.DashboardRepository
import com.knowzeteam.knowze.ui.screen.auth.LoginViewModel
import com.knowzeteam.knowze.utils.UserPreferencesRepository

class ViewModelFactory(
    private val dashboardRepository: DashboardRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(dashboardRepository, userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}