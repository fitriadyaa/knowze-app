package com.knowzeteam.knowze.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int,
    val userName: String?,
    val userEmail: String?,
    val userPhotoUrl: String?
)
