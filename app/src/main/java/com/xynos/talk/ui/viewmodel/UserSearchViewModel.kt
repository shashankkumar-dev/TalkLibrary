package com.xynos.talk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xynos.talk.Utils
import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.User
import com.xynos.talk.repository.ChatRepository
import com.xynos.talk.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository,
    private val cache: UserPreferences
) : ViewModel() {

    val users = MutableStateFlow<List<User>>(emptyList())
    val searchResult = MutableStateFlow<List<User>>(emptyList())
    val userId = cache.getCurrentUserId()


    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.value = withContext(Dispatchers.IO) {
                userRepository.getAllFriends()
            }
        }
    }

    fun searchUser(query: String) {
        viewModelScope.launch {
            searchResult.value = users.value.filter { it.name.contains(query, true) }
        }
    }

    fun selectUser(user: User, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            val chatId = withContext(Dispatchers.IO) {
                val chatId = Utils.getUniqueId(userId, user.id)
                val chat = chatRepository.getChat(chatId)
                if (chat == null) {
                    chatRepository.addChat(chatId, user)
                }
                chatId
            }
            onComplete(chatId)
        }
    }


}