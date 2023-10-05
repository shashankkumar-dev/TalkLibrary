package com.xynos.talk.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chat: Chat) {
    Scaffold(
        topBar = {
            ChatTopBar(chat.photoUrl, chat.user2)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                MessageList(chat.messages)
                MessageBox()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {

    val message = Message(
        id = "1",
        sender = "Sender",
        receiver = "Receiver",
        text = "Hello, how are you?",
        timestamp = System.currentTimeMillis(),
    )
    ChatScreen(
        Chat(
            user1 = "John Doe",
            user2 = "Jane Smith",
            photoUrl = "https://example.com/path_to_image1.jpg",
            messages = listOf(message)
        ),
    )
}
