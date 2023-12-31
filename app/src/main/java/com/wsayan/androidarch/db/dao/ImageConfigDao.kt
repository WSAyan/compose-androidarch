package com.wsayan.androidarch.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wsayan.androidarch.db.entity.ImagesConfig
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageConfigDao {
    @Query("SELECT * FROM ImagesConfig ORDER BY id DESC LIMIT 1")
    fun getLatestConfig(): Flow<ImagesConfig>

    @Query("SELECT CASE WHEN EXISTS(SELECT 1 FROM ImagesConfig) THEN 1 ELSE 0 END")
    fun isEmptyConfig(): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imagesConfig: ImagesConfig)
}