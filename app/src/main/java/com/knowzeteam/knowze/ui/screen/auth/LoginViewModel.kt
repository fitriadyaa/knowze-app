package com.knowzeteam.knowze.ui.screen.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.knowzeteam.knowze.data.remote.response.authResponse.DashboardResponse
import com.knowzeteam.knowze.repository.DashboardRepository
import com.knowzeteam.knowze.utils.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val dashboardRepository: DashboardRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val _idToken = MutableLiveData<String?>()
    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> = _error
    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _dashboardData = MutableLiveData<DashboardResponse?>()
    val dashboardData: LiveData<DashboardResponse?> = _dashboardData

    fun loginWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        _isLoading.value = true

        val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    retrieveIdToken(user)
                    fetchDashboardData()
                } else {
                    _error.value = task.exception
                    Log.e("LoginViewModel", "Firebase authentication failed", task.exception)
                }
            }
    }

    private fun retrieveIdToken(user: FirebaseUser?) {
        user?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idTokenResult = task.result?.token
                _idToken.value = idTokenResult
                idTokenResult?.let { userPreferencesRepository.saveToken(it) }
            } else {
                _error.value = task.exception
                Log.e("LoginViewModel", "Error retrieving Firebase ID Token", task.exception)
            }
        }
    }

    fun logout() {
        userPreferencesRepository.logOut()
        viewModelScope.launch {
            try {
                _isLoading.value = true
                auth.signOut()
                _isLoggedOut.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchDashboardData() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = dashboardRepository.getDashboard()
            _isLoading.value = false
            result.onSuccess { data ->
                _dashboardData.value = data
            }.onFailure { exception ->
                Log.e("LoginViewModel", "Error fetching dashboard data", exception)
            }
        }
    }
}
