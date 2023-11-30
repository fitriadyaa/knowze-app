package com.knowzeteam.knowze.ui.screen.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
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
                    _isAuthenticated.value = task.isSuccessful
                }
        } else {
            // Handle the case where the ID token is null
            Log.d("LoginViewModel", "Google ID Token is null")
        }
    }
}
