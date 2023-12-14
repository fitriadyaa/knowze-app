package com.knowzeteam.knowze.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfileEntity)

    @Query("SELECT * FROM user_profile WHERE id = :userId")
    suspend fun getUserProfile(userId: Int): UserProfileEntity?
}
