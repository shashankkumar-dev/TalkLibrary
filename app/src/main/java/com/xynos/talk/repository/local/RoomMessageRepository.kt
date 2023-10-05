package com.xynos.talk.repository.local

import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message
import com.xynos.talk.repository.MessageRepository

class RoomMessageRepository(private val messageDao: MessageDao) : MessageRepository {

    override fun getAllMessages(chat: Chat, limit: Int, offset: Int): List<Message> {
        return messageDao.getAllMessagesForChat(chat.id, limit, offset)
    }

    override fun getMessage(id: String): Message {
        return messageDao.getMessage(id)
    }

    override fun addMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    override fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message)
    }
}
