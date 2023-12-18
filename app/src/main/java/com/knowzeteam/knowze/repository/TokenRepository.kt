package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.local.AppDatabase
import com.knowzeteam.knowze.data.local.TokenEntity

class TokenRepository(private val appDatabase: AppDatabase) {

    suspend fun saveToken(token: String) {
        try {
            val tokenEntity = TokenEntity(token = token)
            appDatabase.tokenDao().insertToken(tokenEntity)
        } catch (e: Exception) {
            // Handle the exception, possibly log it or inform the user
        }
    }

    suspend fun getToken(): String? {
        return try {
            appDatabase.tokenDao().getToken()?.token
        } catch (e: Exception) {
            // Handle the exception
            null
        }
    }
}
