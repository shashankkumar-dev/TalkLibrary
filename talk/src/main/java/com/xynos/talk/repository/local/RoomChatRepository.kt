package com.xynos.talk.repository.local

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.data.User
import com.xynos.talk.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomChatRepository @Inject constructor(
    private val chatDao: ChatDao,
    private val cache: UserPreferences
) : ChatRepository {

    override fun getAllChats(): Flow<List<Chat>> {
        return chatDao.getAllChats()
    }

    override fun getAllChatsWithMessage(): Flow<List<ChatWithMessages>> {
        return chatDao.getChatWithMessages()
    }

    override suspend fun getChat(id: String): Chat? {
        return chatDao.getChat(id)
    }

    override suspend fun addChat(id: String, user: User): Chat {
        val newChat = Chat(id = id, user1 = cache.getCurrentUserId(), user2 = user.name)
        chatDao.insertChat(newChat)
        return newChat
    }

    override suspend fun updatePhoto(chat: Chat, url: String) {
        val newChat = chat.copy(photoUrl = url)
        chatDao.insertChat(newChat)
    }

    override suspend fun deleteChat(chat: Chat) {
        chatDao.deleteChat(chat)
    }

    override suspend fun getChatsForUser(userId: String): Chat? {
        return chatDao.getChatsForUser(userId).firstOrNull()
    }

    suspend fun addChat(chat: Chat) {
        chatDao.insertChat(chat)
    }
}
