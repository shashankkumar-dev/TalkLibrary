package com.xynos.talk.repository.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.Chat
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.data.User
import com.xynos.talk.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseChatRepository @Inject constructor(
    private val cache: UserPreferences

) : ChatRepository {

    private val db = Firebase.firestore
    private val chatCollection = db.collection("chats")

    override fun getAllChats(): Flow<List<Chat>> = flow {
        val snapshot = chatCollection.get().await()
        val chats = snapshot.toObjects(Chat::class.java)
        emit(chats)
    }.flowOn(Dispatchers.IO)

    override fun getAllChatsWithMessage(): Flow<List<ChatWithMessages>> = flow {
        emit(emptyList<ChatWithMessages>())
    }.flowOn(Dispatchers.IO)

    override suspend fun getChat(id: String): Chat? {
        val document = chatCollection.document(id).get().await()
        return document.toObject(Chat::class.java)
    }

    override suspend fun addChat(id: String, user: User): Chat {
        val chat = Chat(id = id, user1 = user.id, user2 = cache.getCurrentUserName())
        chatCollection.document(id).set(chat).await()
        return chat
    }

    override suspend fun updatePhoto(chat: Chat, url: String) {
        val chatDocument = chatCollection.document(chat.id)
        chatDocument.update("photoUrl", url).await()
    }

    override suspend fun deleteChat(chat: Chat) {
        chatCollection.document(chat.id).delete().await()
    }

    override suspend fun getChatsForUser(userId: String): Chat? {
        val snapshot = chatCollection.whereEqualTo("user1", userId).get().await()
        val chats = snapshot.toObjects(Chat::class.java)
        return chats.firstOrNull()
    }
}
