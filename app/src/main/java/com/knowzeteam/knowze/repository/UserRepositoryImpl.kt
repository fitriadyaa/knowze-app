package com.knowzeteam.knowze.repository

import android.content.Context
import com.knowzeteam.knowze.data.local.UserProfileDao
import com.knowzeteam.knowze.data.local.UserProfileEntity
import com.knowzeteam.knowze.utils.Preference

class UserRepositoryImpl(private val context: Context,private val userProfileDao: UserProfileDao) : UserRepository {

    override suspend fun getUserProfile(userId: Int): UserProfileEntity? {
        return userProfileDao.getUserProfile(userId)
    }
    override suspend fun insertUserProfile(userProfile: UserProfileEntity) {
        userProfileDao.insertUserProfile(userProfile)
    }
    override suspend fun saveUserProfile(user: UserProfileEntity) {
        userProfileDao.insertUserProfile(user)
    }
    override fun setLoginStatus(isLoggedIn: Boolean) {
        Preference.setLoginStatus(context, isLoggedIn)
    }

    override fun getLoginStatus(): Boolean {
        return Preference.checkLoginStatus(context)
    }
}
