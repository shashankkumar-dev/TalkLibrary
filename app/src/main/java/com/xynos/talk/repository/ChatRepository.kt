package com.xynos.talk.repository

import com.xynos.talk.data.Chat
import com.xynos.talk.data.User

interface ChatRepository {
    fun getAllChats(): List<Chat>
    fun getChat(id: String): Chat?
    fun addChat(user: User): Chat
    fun updatePhoto(url: String)
    fun deleteChat(chat: Chat)
    fun getChatsForUser(userId: String): Chat?
}