package com.xynos.talk.repository.local

import com.xynos.talk.data.Message
import com.xynos.talk.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomMessageRepository @Inject constructor(private val messageDao: MessageDao) :
    MessageRepository {

    override fun getAllMessages(chatId: String, limit: Int, offset: Int): Flow<List<Message>> {
        return messageDao.getAllMessagesForChat(chatId, limit, offset)
    }

    override suspend fun getMessage(id: String): Message {
        return messageDao.getMessage(id)
    }

    override suspend fun addMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    override suspend fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message)
    }
}
