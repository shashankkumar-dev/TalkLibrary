package com.xynos.talk.repository.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xynos.talk.data.Message;
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    fun getAllMessagesForChat(chatId: String, limit: Int, offset: Int): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE id = :messageId")
    fun getMessage(messageId: String): Message

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Long

    @Delete
    fun deleteMessage(message: Message): Int
}
