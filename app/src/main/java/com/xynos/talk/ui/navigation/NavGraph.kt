package com.xynos.talk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xynos.talk.ui.screen.ChatListScreen
import com.xynos.talk.ui.screen.ChatScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.ChatListScreen.route) {
        composable(Screen.ChatListScreen.route) {
            ChatListScreen(navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController)
        }
    }
}
