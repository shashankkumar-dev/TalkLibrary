package com.xynos.talk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.domain.SyncUseCase
import com.xynos.talk.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    private val _chats = MutableStateFlow<List<ChatWithMessages>>(emptyList())
    val chats: StateFlow<List<ChatWithMessages>> = _chats

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                syncUseCase.registerUser("Shashankk  ")
            }
            chatRepository.getAllChatsWithMessage().flowOn(Dispatchers.IO).collect {
                val list = it.filter { chatWithMessages -> chatWithMessages.messages.isNotEmpty() }
                _chats.value = list
            }
            launch {
                withContext(Dispatchers.IO) {
                    syncUseCase.syncChats()
                }
            }
        }
    }


}
