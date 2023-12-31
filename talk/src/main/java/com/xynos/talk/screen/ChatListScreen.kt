package com.xynos.talk.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xynos.talk.common.ChatTile
import com.xynos.talk.common.SetStatusBarColor
import com.xynos.talk.data.ChatWithMessages
import com.xynos.talk.navigation.Screen
import com.xynos.talk.ui.theme.BackgroundBlue
import com.xynos.talk.ui.theme.GrayBlue
import com.xynos.talk.viewmodel.ChatListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController, viewModel: ChatListViewModel = hiltViewModel()) {
    val chats by viewModel.chats.collectAsState()
    SetStatusBarColor()
    Scaffold(topBar = {
        ChatListTopBar(navController)
    }, content = {
        ChatListContent(chats, navController)
    })
}

@Composable
private fun ChatListContent(
    chats: List<ChatWithMessages>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundBlue)
            .padding(top = 64.dp, start = 16.dp, end = 16.dp)
    ) {

        LazyColumn {
            items(
                chats.size,
                key = { index -> chats[index].chat.id }
            ) { index ->
                val chat = chats[index]

                ChatTile(chat) {
                    navController.navigate(Screen.ChatScreen.route(chat.chat.id))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ChatListTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Talk") },
        actions = {
            IconButton(onClick = { navController.navigate(Screen.UserSearchScreen.route) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = GrayBlue,)
    )
}

