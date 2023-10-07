package com.xynos.talk.repository.local

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.data.User
import com.xynos.talk.repository.ChatRepository
import javax.inject.Inject

class RoomChatRepository @Inject constructor(
    private val chatDao: ChatDao,
    private val cache: UserPreferences
) : ChatRepository {

    override fun getAllChats(): List<Chat> {
        return chatDao.getAllChats()
    }

    override fun getAllChatsWithMessage(): List<ChatWithMessages> {
        return chatDao.getChatWithMessages()
    }

    override fun getChat(id: String): Chat? {
        return chatDao.getChat(id)
    }

    override fun addChat(user: User): Chat {
        val newChat = Chat(user1 = cache.getCurrentUserId(), user2 = user.name)
        val chatId = chatDao.insertChat(newChat)
        return newChat.copy(id = chatId.toString())
    }

    override fun updatePhoto(chat: Chat, url: String) {
        val newChat = chat.copy(photoUrl = url)
        chatDao.insertChat(newChat)
    }

    override fun deleteChat(chat: Chat) {
        chatDao.deleteChat(chat)
    }

    override fun getChatsForUser(userId: String): Chat? {
        return chatDao.getChatsForUser(userId).firstOrNull()
    }
}
