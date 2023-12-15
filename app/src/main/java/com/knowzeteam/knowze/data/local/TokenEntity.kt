package com.knowzeteam.knowze.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_token")
data class TokenEntity(
    @PrimaryKey val id: Int = 0,
    val token: String
)
