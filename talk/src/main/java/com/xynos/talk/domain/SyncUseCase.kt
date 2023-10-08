package com.xynos.talk.domain

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.Message
import com.xynos.talk.data.User
import com.xynos.talk.repository.firebase.FirebaseChatRepository
import com.xynos.talk.repository.firebase.FirebaseMessageRepository
import com.xynos.talk.repository.firebase.FirebaseUserRepository
import com.xynos.talk.repository.local.RoomChatRepository
import com.xynos.talk.repository.local.RoomMessageRepository
import com.xynos.talk.repository.local.RoomUserRepository
import javax.inject.Inject

class SyncUseCase @Inject  constructor(
    private val firebaseChatRepository: FirebaseChatRepository,
    private val roomChatRepository: RoomChatRepository,
    private val firebaseUserRepository: FirebaseUserRepository,
    private val roomUserRepository: RoomUserRepository,
    private val firebaseMessageRepository: FirebaseMessageRepository,
    private val roomMessageRepository: RoomMessageRepository,
    private val cache: UserPreferences
) {

    suspend fun syncUsers() {
        val users = firebaseUserRepository.getAllFriends()
        users.forEach {
            roomUserRepository.addFriend(it)
        }
    }

    suspend fun syncChats() {
        firebaseChatRepository.getAllChats().collect{ chats ->
            chats.forEach {
                roomChatRepository.addChat(it)
            }
        }
    }

    suspend fun syncMessages(id: String) {
        firebaseMessageRepository.getAllMessages(id).collect{ messages ->
            messages.forEach {
                roomMessageRepository.addMessage(it)
            }
        }
    }

    suspend fun registerUser(name: String) {
        val user = firebaseUserRepository.registerUser(name)
        roomUserRepository.addFriend(user)
        cache.setCurrentUserId(user.id)
        cache.setCurrentUserName(name)
    }

    suspend fun sendMessage(message: Message) {
        firebaseMessageRepository.addMessage(message)
        roomMessageRepository.addMessage(message)
    }

    suspend fun addChat(chatId: String, user: User) {
        firebaseChatRepository.addChat(chatId, user)
        roomChatRepository.addChat(chatId, user)
    }

}