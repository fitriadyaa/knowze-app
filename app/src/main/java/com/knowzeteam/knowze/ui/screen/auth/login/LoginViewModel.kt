package com.knowzeteam.knowze.ui.screen.auth.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.knowzeteam.knowze.data.local.UserProfileEntity
import com.knowzeteam.knowze.data.remote.response.dashboard.CoursesItem
import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import com.knowzeteam.knowze.repository.TokenRepository
import com.knowzeteam.knowze.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val apiService: ApiService,
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName.asStateFlow()

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail.asStateFlow()

    private val _userPhotoUrl = MutableStateFlow<String?>(null)
    val userPhotoUrl: StateFlow<String?> = _userPhotoUrl.asStateFlow()

    private val _newestCourses = MutableStateFlow<List<CoursesItem?>?>(null)
    val newestCourses: StateFlow<List<CoursesItem?>?> = _newestCourses.asStateFlow()


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
                    val authResult = firebaseAuth.signInWithCredential(credential).await()

                    // Check if the authentication was successful
                    if (authResult.user != null) {
                        val user = authResult.user
                        val userId = user?.uid ?: return@launch
                        val userName = user.displayName
                        val userEmail = user.email
                        val userPhotoUrl = user.photoUrl?.toString()

                        // Check for null values and save the user profile
                        val userProfile: UserProfileEntity? = if (!userName.isNullOrEmpty() && !userEmail.isNullOrEmpty()) {
                            val userProfileToSave = UserProfileEntity(
                                id = userId,
                                userName = userName,
                                userEmail = userEmail,
                                userPhotoUrl = userPhotoUrl
                            )
                            userRepository.saveUserProfile(userProfileToSave)

                            // Assign the userProfile object to the one you just saved
                            userProfileToSave
                        } else {
                            // If any of the fields is null or empty, set userProfile to null
                            null
                        }

                        userProfile?.let { userProfileEntity ->
                            _userName.value = userProfileEntity.userName
                            _userEmail.value = userProfileEntity.userEmail
                            _userPhotoUrl.value = userProfileEntity.userPhotoUrl
                        }

                        val token = getFirebaseAuthToken()

                        if (token != null) {
                            tokenRepository.saveToken(token)
                            val response = apiService.getDashboardData("Bearer $token")
                            if (response.isSuccessful) {
                                _loginState.value = LoginState.Success(response.body())

                                val dashboardResponse = response.body()
                                if (dashboardResponse != null) {
                                    val courses = dashboardResponse.courses
                                    if (!courses.isNullOrEmpty()) {
                                        // Sort the courses by timestamp in descending order to get the newest ones first
                                        val sortedCourses = courses.sortedByDescending { it?.timestamp ?: 0 }

                                        // Save all courses, not just the two newest ones
                                        _newestCourses.value = sortedCourses

                                        // Update the login state
                                        _loginState.value = LoginState.Success(dashboardResponse)
                                    }
                                }
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