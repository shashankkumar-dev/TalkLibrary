package com.xynos.talk.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatListViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val chats = mutableStateOf<List<ChatWithMessages>>(emptyList())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                registerUser("Android")
                val allChats = chatRepository.getAllChatsWithMessage()

                // Switch to Main thread to update the state
                withContext(Dispatchers.Main) {
                    chats.value = allChats
                }
            }
        }
    }

    private fun registerUser(name: String) {
        val url = "https://static.vecteezy.com/system/resources/previews/007/301/307/original/flat-cartoon-character-illustration-boy-people-icon-afro-man-portrait-avatar-head-indian-user-for-web-sites-and-applications-stock-design-vector.jpg"
        val user2 = userRepository.registerUser(name)
        userRepository.updatePhoto(url)
        val user1 = userRepository.registerUser(name)
        userRepository.updatePhoto(url)
        chatRepository.addChat(user2)
    }

}
