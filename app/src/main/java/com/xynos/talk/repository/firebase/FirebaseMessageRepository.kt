package com.xynos.talk.repository.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xynos.talk.data.Message
import com.xynos.talk.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMessageRepository @Inject constructor(): MessageRepository {

    private val db = Firebase.firestore
    private val messageCollection = db.collection("messages")

    override fun getAllMessages(chatId: String, limit: Int, offset: Int): Flow<List<Message>> = flow {
        val querySnapshot = messageCollection
            .whereEqualTo("chatId", chatId)
            .get()
            .await()

        val messages = querySnapshot.toObjects(Message::class.java)
        emit(messages)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMessage(id: String): Message {
        val document = messageCollection.document(id).get().await()
        return document.toObject(Message::class.java) ?: throw NoSuchElementException("Message with ID $id not found")
    }

    override suspend fun addMessage(message: Message) {
        messageCollection.add(message).await()
    }

    override suspend fun deleteMessage(message: Message) {
        messageCollection.document(message.id).delete().await()
    }
}
