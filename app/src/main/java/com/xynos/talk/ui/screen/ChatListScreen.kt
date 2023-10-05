package com.xynos.talk.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(chats: List<Chat>) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of Chat Tiles
        val filteredChats = chats.filter {
            it.user1.contains(searchQuery, ignoreCase = true) ||
                    it.user2.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn {
            items(filteredChats.size) { index ->
                val chat = filteredChats[index]
                ChatTile(chat)
                Spacer(modifier = Modifier.height(8.dp)) // Spacing between tiles
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen1() {
    val message = Message(
        id = "1",
        sender = "Sender",
        receiver = "Receiver",
        text = "Hello, how are you?",
        timestamp = System.currentTimeMillis(),
    )
    val sampleChats = listOf(
        Chat(user1 = "John Doe", user2 = "Jane Smith", photoUrl = "https://example.com/path_to_image1.jpg", messages = listOf(message)),
        Chat(user1 = "Alice", user2 = "Bob", photoUrl = "https://example.com/path_to_image2.jpg", messages = listOf(message)),
        Chat(user1 = "Alice", user2 = "Bob", photoUrl = "https://example.com/path_to_image2.jpg", messages = listOf(message)),
        Chat(user1 = "Alice", user2 = "Bob", photoUrl = "https://example.com/path_to_image2.jpg", messages = listOf(message))
    )
    ChatListScreen(sampleChats)
}
