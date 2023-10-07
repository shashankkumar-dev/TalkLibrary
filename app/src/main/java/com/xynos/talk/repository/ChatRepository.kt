package com.xynos.talk.repository

import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.data.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChats(): Flow<List<Chat>>
    fun getAllChatsWithMessage(): Flow<List<ChatWithMessages>>
    fun getChat(id: String): Chat?
    fun addChat(user: User): Chat
    fun updatePhoto(chat: Chat, url: String)
    fun deleteChat(chat: Chat)
    fun getChatsForUser(userId: String): Chat?
}