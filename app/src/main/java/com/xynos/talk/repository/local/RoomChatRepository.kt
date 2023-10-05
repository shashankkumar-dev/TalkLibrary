package com.xynos.talk.repository.local

import com.xynos.talk.data.Chat
import com.xynos.talk.data.User
import com.xynos.talk.repository.ChatRepository

class RoomChatRepository(private val chatDao: ChatDao) : ChatRepository {

    override fun getAllChats(): List<Chat> {
        return chatDao.getAllChats()
    }

    override fun getChat(id: String): Chat? {
        return chatDao.getChat(id)
    }

    override fun addChat(user: User): Chat {
        val newChat = Chat(user1 = user.id, user2 = "") // Adjust based on your actual Chat structure
        val chatId = chatDao.insertChat(newChat)
        // This assumes Chat has a copy method (data class). Adjust accordingly.
        return newChat.copy(id = chatId.toString())
    }

    override fun updatePhoto(url: String) {
        // This needs a specific chat ID to update. Adjust the method signature or provide more context.
        // For example:
        // chatDao.updatePhoto(specificChatId, url)
    }

    override fun deleteChat(chat: Chat) {
        chatDao.deleteChat(chat)
    }

    override fun getChatsForUser(userId: String): Chat? {
        return chatDao.getChatsForUser(userId).firstOrNull()
        // Note: This retrieves the first chat for a user. If there's a possibility of multiple chats,
        // you might need to change the return type to List<Chat> and return the full list.
    }
}
