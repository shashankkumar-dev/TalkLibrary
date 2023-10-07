package com.xynos.talk.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message
import com.xynos.talk.ui.common.ChatTopBar
import com.xynos.talk.ui.common.MessageBox
import com.xynos.talk.ui.common.MessageList
import com.xynos.talk.ui.viewmodel.ChatViewModel

@Composable
fun ChatScreen(navController: NavHostController, viewModel: ChatViewModel = hiltViewModel()) {
    val chatId = navController.currentBackStackEntry?.arguments?.getString("chatId")
    LaunchedEffect(chatId) {
        viewModel.loadData(chatId!!)
    }

    val messages = viewModel.messages.value
    val chat = viewModel.chat.value ?: return
    val onClick = { message: String ->
        viewModel.sendMessage(message)
    }
    ChatScreenScaffold(chat, messages, onClick)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ChatScreenScaffold(
    chat: Chat,
    messages: List<Message>,
    onClick: (String) -> Unit
) {
    Scaffold(topBar = {
        ChatTopBar(chat.photoUrl, chat.user2)
    }, content = {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            MessageList(messages)
            MessageBox(onClick)
        }
    })
}
