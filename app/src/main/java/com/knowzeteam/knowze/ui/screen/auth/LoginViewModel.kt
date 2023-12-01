package com.knowzeteam.knowze.ui.screen.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel  : ViewModel(){
    private val auth: FirebaseAuth = Firebase.auth

    // LiveData to observe the authentication state
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated: StateFlow<Boolean?> = _isAuthenticated

    fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {

        val idToken = googleSignInAccount.idToken
        if (idToken != null) {
            // Log the ID token (for debugging purposes)
            Log.d("LoginViewModel", "Google ID Token: $idToken")

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    }
                    _isAuthenticated.value = task.isSuccessful
                }
        } else {
            // Handle the case where the ID token is null
            Log.d("LoginViewModel", "Google ID Token is null")
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
