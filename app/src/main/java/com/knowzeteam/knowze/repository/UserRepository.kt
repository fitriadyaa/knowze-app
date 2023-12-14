package com.knowzeteam.knowze.repository

import android.content.Context
import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.local.UserProfileEntity

interface UserRepository {
    suspend fun getUserProfile(userId: Int): UserProfileEntity?
    suspend fun insertUserProfile(userProfile: UserProfileEntity)

    suspend fun saveUserProfile(user: UserProfileEntity)

    fun setLoginStatus(isLoggedIn: Boolean)
    fun getLoginStatus(): Boolean

    companion object {
        @JvmStatic
        fun create(context: Context, userProfileDao: UserProfileDao): UserRepository {
            return UserRepositoryImpl(context, userProfileDao)
        }
    }
}
