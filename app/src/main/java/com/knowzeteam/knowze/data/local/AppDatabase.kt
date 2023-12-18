package com.knowzeteam.knowze.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserProfileEntity::class, TokenEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun tokenDao(): TokenDao
}