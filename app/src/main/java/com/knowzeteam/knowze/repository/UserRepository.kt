package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.local.UserProfileEntity

interface UserRepository {
    suspend fun getUserProfile(userId: Int): UserProfileEntity?
    suspend fun insertUserProfile(userProfile: UserProfileEntity)

    companion object {
        @JvmStatic
        fun create(userProfileDao: UserProfileDao): UserRepository {
            return UserRepositoryImpl(userProfileDao)
        }
    }
}
