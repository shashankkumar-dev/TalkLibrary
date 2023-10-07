package com.xynos.talk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xynos.talk.repository.local.ChatDao
import com.xynos.talk.repository.local.MessageDao
import com.xynos.talk.repository.local.UserDao

@Database(entities = [User::class, Message::class, Chat::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    abstract fun chatDao(): ChatDao

    companion object {
        const val DATABASE_NAME = "talk.db"
    }
}