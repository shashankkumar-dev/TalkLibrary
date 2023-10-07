package com.xynos.talk.ui.navigation

sealed class Screen(val route: String) {
    object ChatListScreen : Screen("chatList")
    object ChatScreen : Screen("chat")
}
