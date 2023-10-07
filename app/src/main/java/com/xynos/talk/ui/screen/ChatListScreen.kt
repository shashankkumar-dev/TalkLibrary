package com.xynos.talk.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xynos.talk.ui.navigation.Screen
import com.xynos.talk.ui.viewmodel.ChatListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController, viewModel: ChatListViewModel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val chats = viewModel.chats.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of Chat Tiles
        val filteredChats = chats.filter {
            it.chat.user1.contains(searchQuery, ignoreCase = true) || it.chat.user2.contains(
                searchQuery,
                ignoreCase = true
            )
        }

        LazyColumn {
            items(filteredChats.size) { index ->
                val chat = filteredChats[index]
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.ChatScreen.route(chat.chat.id))
                    }) {
                    ChatTile(chat)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen1() {}
