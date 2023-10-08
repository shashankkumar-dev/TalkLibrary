package com.xynos.talk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xynos.talk.screen.ChatListScreen
import com.xynos.talk.screen.ChatScreen
import com.xynos.talk.screen.UserSearchScreen

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
        composable(Screen.UserSearchScreen.route) {
            UserSearchScreen(navController)
        }
    }
}
