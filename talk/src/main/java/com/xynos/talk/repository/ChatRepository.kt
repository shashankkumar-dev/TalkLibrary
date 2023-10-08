package com.xynos.talk.repository

import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.data.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChats(): Flow<List<Chat>>
    fun getAllChatsWithMessage(): Flow<List<ChatWithMessages>>
    suspend fun getChat(id: String): Chat?
    suspend fun addChat(id: String, user: User): Chat
    suspend fun updatePhoto(chat: Chat, url: String)
    suspend fun deleteChat(chat: Chat)
    suspend fun getChatsForUser(userId: String): Chat?
}