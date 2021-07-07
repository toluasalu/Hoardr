package com.zuri.pjt_95_hoardr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zuri.pjt_95_hoardr.dao.UserDao
import com.zuri.pjt_95_hoardr.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}