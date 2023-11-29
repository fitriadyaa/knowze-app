package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.remote.firebase.GoogleSignInService

class AuthRepository(private val googleSignInService: GoogleSignInService) {

    suspend fun signInWithGoogle(token: String): Boolean {
        return googleSignInService.signInWithGoogle(token)
    }
}