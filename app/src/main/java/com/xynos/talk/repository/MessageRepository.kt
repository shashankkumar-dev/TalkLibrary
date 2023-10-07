package com.xynos.talk.repository

import com.xynos.talk.data.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessages(chatId: String, limit: Int = 15, offset: Int = 0): Flow<List<Message>>
    fun getMessage(id: String): Message
    fun addMessage(message: Message)
    fun deleteMessage(message: Message)
}