package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.local.UserProfileEntity

class UserRepository(private val userProfileDao: UserProfileDao) {

    suspend fun saveUserProfile(userProfile: UserProfileEntity) {
        userProfileDao.insertUserProfile(userProfile)
    }

    suspend fun getUserProfile(userId: Int): UserProfileEntity? {
        return userProfileDao.getUserProfile(userId)
    }
}
