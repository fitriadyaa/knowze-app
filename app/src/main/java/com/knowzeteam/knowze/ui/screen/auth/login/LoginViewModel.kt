package com.knowzeteam.knowze.ui.screen.auth.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val auth: FirebaseAuth = Firebase.auth

    fun googleLogin(googleSignInAccount: GoogleSignInAccount) {

        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading
                // Get the Google ID token from the GoogleSignInAccount
                val idToken = googleSignInAccount.idToken
                if (idToken != null) {
                    // Create Firebase credential with the Google ID token
                    val credential = GoogleAuthProvider.getCredential(idToken, null)

                    // Sign in with Firebase using the credential
                    val authResult = auth.signInWithCredential(credential).await()

                    // Check if the authentication was successful
                    if (authResult.user != null) {
                        // Authentication successful, you can proceed with fetching the Firebase token and other actions.
                        val token = getFirebaseAuthToken()
                        if (token != null) {
                            val response = apiService.getDashboardData("Bearer $token")
                            if (response.isSuccessful) {
                                _loginState.value = LoginState.Success(response.body())
                            } else {
                                val errorMessage = "API call failed: ${response.errorBody()?.string()}"
                                _loginState.value = LoginState.Error(errorMessage)
                                Log.e(TAG, errorMessage)
                            }
                        } else {
                            val errorMessage = "Failed to get Firebase token"
                            _loginState.value = LoginState.Error(errorMessage)
                            Log.e(TAG, errorMessage)
                        }
                    } else {
                        val errorMessage = "Firebase authentication failed"
                        _loginState.value = LoginState.Error(errorMessage)
                        Log.e(TAG, errorMessage)
                    }
                } else {
                    val errorMessage = "Google ID token is null"
                    _loginState.value = LoginState.Error(errorMessage)
                    Log.e(TAG, errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Error during login: ${e.message ?: "Unknown error"}"
                _loginState.value = LoginState.Error(errorMessage)
                Log.e(TAG, errorMessage, e)
            }
        }
    }


    private suspend fun getFirebaseAuthToken(): String? {
        return try {
            val mUser = firebaseAuth.currentUser
            if (mUser != null) {
                // User is authenticated, proceed to get the token
                val tokenResult = mUser.getIdToken(true).await()
                val token = tokenResult.token
                if (token != null) {
                    // Log the token when it's successfully retrieved
                    Log.d(TAG, "Firebase token: $token")
                    token
                } else {
                    val errorMessage = "Firebase token is null"
                    Log.e(TAG, errorMessage)
                    null
                }
            } else {
                val errorMessage = "User is not authenticated with Firebase"
                Log.e(TAG, errorMessage)
                null
            }
        } catch (e: Exception) {
            val errorMessage = "Error getting Firebase token: ${e.message ?: "Unknown error"}"
            Log.e(TAG, errorMessage, e)
            null
        }
    }



    fun logout() {
        viewModelScope.launch {
            try {
                firebaseAuth.signOut()
                _loginState.value = LoginState.Logout
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Logout failed")
            }
        }
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState() // Add loading state
        object Logout : LoginState()
        data class Success(val dashboardResponse: DashboardResponse?) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
