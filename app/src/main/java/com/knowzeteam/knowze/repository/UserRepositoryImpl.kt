package com.knowzeteam.knowze.repository

import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.local.UserProfileEntity
class UserRepositoryImpl(private val userProfileDao: UserProfileDao) : UserRepository {

    override suspend fun getUserProfile(userId: Int): UserProfileEntity? {
        return userProfileDao.getUserProfile(userId)
    }

    override suspend fun insertUserProfile(userProfile: UserProfileEntity) {
        userProfileDao.insertUserProfile(userProfile)
    }
}
