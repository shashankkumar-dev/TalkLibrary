package com.xynos.talk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.UserRepository
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
    private val userRepository: UserRepository
) : ViewModel() {

    private val _chats = MutableStateFlow<List<ChatWithMessages>>(emptyList())
    val chats: StateFlow<List<ChatWithMessages>> = _chats

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //registerUser()
            }
            chatRepository.getAllChatsWithMessage().flowOn(Dispatchers.IO).collect {
                _chats.value = it
            }
        }
    }

    private fun registerUser() {
        val url =
            "https://static.vecteezy.com/system/resources/previews/007/301/307/original/flat-cartoon-character-illustration-boy-people-icon-afro-man-portrait-avatar-head-indian-user-for-web-sites-and-applications-stock-design-vector.jpg"
        val user2 = userRepository.registerUser("User 2")
        userRepository.updatePhoto(url)
        userRepository.registerUser("User 3")
        userRepository.updatePhoto(url)
        //chatRepository.addChat(user2)
    }

}
