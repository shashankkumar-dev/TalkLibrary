package com.xynos.talk.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository
): ViewModel(){

    val chat = mutableStateOf<Chat?>(null)
    val messages = mutableStateOf<List<Message>>(emptyList())
    var offset = 0


    fun loadData(chatId: String) {
        loadChat(chatId)
        loadMessages(chatId)
    }

    private fun loadChat(chatId: String) {
        viewModelScope.launch {
            chat.value = withContext(Dispatchers.IO) {
                chatRepository.getChat(chatId)
            }
        }
    }

    private fun loadMessages(chatId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newMessages = messageRepository.getAllMessages(chatId, LIMIT, offset)
                offset += LIMIT
                messages.value = messages.value.plus(newMessages)
            }
        }
    }

    companion object {
        private const val TAG = "ChatViewModel"
        private const val LIMIT = 15
    }

}