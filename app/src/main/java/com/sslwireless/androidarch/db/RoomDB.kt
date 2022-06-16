package com.sslwireless.androidarch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sslwireless.androidarch.db.dao.ImageConfigDao
import com.sslwireless.androidarch.db.entity.ImagesConfig


@Database(entities = [ImagesConfig::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun imageConfigDao(): ImageConfigDao
}