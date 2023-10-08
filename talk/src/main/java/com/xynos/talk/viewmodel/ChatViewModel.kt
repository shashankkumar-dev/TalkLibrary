package com.xynos.talk.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message
import com.xynos.talk.domain.SyncUseCase
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val cache: UserPreferences,
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    val chat = mutableStateOf<Chat?>(null)
    val messages = mutableStateOf<List<Message>>(emptyList())
    private var offset = 0
    var sender: String = cache.getCurrentUserName()
    private lateinit var receiver: String
    private lateinit var chatId: String

    fun loadData(chatId: String) {
        loadChat(chatId)
        loadMessages(chatId)
    }

    private fun loadChat(chatId: String) {
        viewModelScope.launch {
            chat.value = withContext(Dispatchers.IO) {
                chatRepository.getChat(chatId)
            }
            receiver = getOtherUser(sender)
            this@ChatViewModel.chatId = chat.value!!.id
        }
    }

    private fun loadMessages(chatId: String) {
        viewModelScope.launch {
            messageRepository.getAllMessages(chatId, LIMIT, offset).flowOn(Dispatchers.IO).collect {
                //offset += LIMIT
                messages.value = it.reversed()
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                syncUseCase.syncMessages(chatId)
            }
        }
    }

    fun sendMessage(text: String) {
        Log.i(TAG, "sendMessage: $sender")
        viewModelScope.launch {
            val message = Message(
                text = text,
                sender = sender,
                receiver = receiver,
                chatId = chatId
            )
            withContext(Dispatchers.IO) {
                syncUseCase.sendMessage(message)
            }
        }
    }

    private fun getOtherUser(name: String): String {
        return if (chat.value?.user1 == name) {
            chat.value?.user2!!
        } else {
            chat.value?.user1!!
        }
    }

    companion object {
        private const val TAG = "ChatViewModel"
        private const val LIMIT = 50
    }

}