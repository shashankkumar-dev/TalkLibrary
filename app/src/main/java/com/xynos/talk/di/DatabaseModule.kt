package com.xynos.talk.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.AppDatabase
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.MessageRepository
import com.xynos.talk.repository.UserRepository
import com.xynos.talk.repository.local.ChatDao
import com.xynos.talk.repository.local.MessageDao
import com.xynos.talk.repository.local.RoomChatRepository
import com.xynos.talk.repository.local.RoomMessageRepository
import com.xynos.talk.repository.local.RoomUserRepository
import com.xynos.talk.repository.local.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideMessageDao(database: AppDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    fun provideChatDao(database: AppDatabase): ChatDao {
        return database.chatDao()
    }

    @Provides
    fun provideRoomChatRepository(chatDao: ChatDao, cache: UserPreferences): ChatRepository {
        return RoomChatRepository(chatDao, cache)
    }


    @Provides
    fun provideRoomUserRepository(userDao: UserDao, cache: UserPreferences): UserRepository {
        return RoomUserRepository(userDao, cache)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideUserPreferences(application: Context): UserPreferences {
        return UserPreferences(application)
    }

    @Provides
    fun provideMessageRepository(messageDao: MessageDao): MessageRepository {
        return RoomMessageRepository(messageDao)
    }

}
