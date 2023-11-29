package com.knowzeteam.knowze.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowzeteam.knowze.repository.AuthRepository
import kotlinx.coroutines.launch

class GoogleLoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            try {
                authRepository.signInWithGoogle(idToken)
                onSuccess.invoke()
            } catch (e: Exception) {
                onFailure.invoke("Authentication failed: ${e.message}")
            }
        }
    }
}