package com.xynos.talk.repository.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xynos.talk.data.Message
import com.xynos.talk.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMessageRepository @Inject constructor(): MessageRepository {

    private val db = Firebase.firestore
    private val messageCollection = db.collection("chats")
    override fun getAllMessages(chatId: String, limit: Int, offset: Int): Flow<List<Message>> = callbackFlow {
        val listenerRegistration = messageCollection.document(chatId).collection("messages")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val messages = value?.toObjects(Message::class.java)
                if (messages != null) {
                    this.trySend(messages).isSuccess
                }
            }

        awaitClose { listenerRegistration.remove() }
    }.flowOn(Dispatchers.IO)


    override suspend fun getMessage(id: String): Message {
        val document = messageCollection.document(id).get().await()
        return document.toObject(Message::class.java) ?: throw NoSuchElementException("Message with ID $id not found")
    }

    override suspend fun addMessage(message: Message) {
        messageCollection.document(message.chatId).collection("messages")
            .add(message).await()
    }

    override suspend fun deleteMessage(message: Message) {
        messageCollection.document(message.chatId).collection("messages")
            .document(message.id).delete().await()
    }
}
