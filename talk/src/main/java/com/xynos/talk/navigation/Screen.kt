package com.xynos.talk.navigation

sealed class Screen(val route: String) {
    object ChatListScreen : Screen("chatList")
    object ChatScreen : Screen("chat/{chatId}") {
        fun route(chatId: String) = "chat/$chatId"
    }
    object UserSearchScreen : Screen("userSearch")
}
