package com.xynos.talk.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): List<Chat>

    @Query("SELECT * FROM chats WHERE id = :chatId")
    fun getChat(chatId: String): Chat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(chat: Chat): Long

    @Query("UPDATE chats SET photoUrl = :url WHERE id = :chatId")
    fun updatePhoto(chatId: String, url: String)

    @Delete
    fun deleteChat(chat: Chat): Int

    @Query("SELECT * FROM chats WHERE user1 = :userId OR user2 = :userId")
    fun getChatsForUser(userId: String): List<Chat>

    @Transaction
    @Query("SELECT * FROM chats")
    fun getChatWithMessages(): List<ChatWithMessages>
}
