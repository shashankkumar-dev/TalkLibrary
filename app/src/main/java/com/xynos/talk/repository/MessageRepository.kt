package com.xynos.talk.repository

import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message

interface MessageRepository {
    fun getAllMessages(chat: Chat, limit: Int = 15, offset: Int = 0): List<Message>
    fun getMessage(id: String): Message
    fun addMessage(message: Message)
    fun deleteMessage(message: Message)
}