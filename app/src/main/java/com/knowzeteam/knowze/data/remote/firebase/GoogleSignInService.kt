package com.knowzeteam.knowze.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await


class GoogleSignInService {

    private val auth = FirebaseAuth.getInstance()

    suspend fun signInWithGoogle(idToken: String): Boolean {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            val authResult = auth.signInWithCredential(credential).await()
            authResult.user != null
        } catch (e: Exception) {
            false
        }
    }
}