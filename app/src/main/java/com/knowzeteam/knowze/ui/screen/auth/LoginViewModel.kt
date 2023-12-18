package com.knowzeteam.knowze.ui.screen.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel  : ViewModel(){
    private val auth: FirebaseAuth = Firebase.auth

    private val _idToken = MutableLiveData<String?>()
    val idToken: LiveData<String?> = _idToken

    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> = _error

    fun firebaseAuthWithGoogle() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idTokenResult = task.result?.token
                _idToken.value = idTokenResult

                // Print the ID token
                Log.d("LoginViewModel", "Firebase ID Token: $idTokenResult")
            } else {
                _error.value = task.exception
                Log.d("LoginViewModel", "Error retrieving Firebase ID Token", task.exception)
            }
        }
    }

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun logout() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // Perform logout logic...
                auth.signOut() // Firebase sign out

                _isLoggedOut.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
