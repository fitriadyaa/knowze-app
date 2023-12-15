package com.knowzeteam.knowze.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(tokenEntity: TokenEntity)

    @Query("SELECT * FROM auth_token WHERE id = 0")
    suspend fun getToken(): TokenEntity?
}
