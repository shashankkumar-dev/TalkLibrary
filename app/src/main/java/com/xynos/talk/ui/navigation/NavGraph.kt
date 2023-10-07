package com.xynos.talk.ui.navigation

import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xynos.talk.ui.viewmodel.ChatListViewModel

@Composable
fun NavGraph(viewModelFactory: ViewModelProvider.Factory) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.ChatListScreen.route) {
        composable(Screen.ChatListScreen.route) {
            val viewModel = LocalSavedStateRegistryOwner.current.getViewModelStore().get(ChatListViewModel::class.java)

            val viewModel by viewModels<ChatListViewModel> { viewModelFactory }

            ChatListScreen(navController, viewModel)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController)
        }
    }
}

@Composable
fun ChatListScreen(navController: NavController) {
    // Your UI components...
    Button(onClick = {
        navController.navigate(Screen.ChatScreen.route)
    }) {
        Text("Go to Chat")
    }
}

@Composable
fun ChatScreen(navController: NavController) {
    // Your UI for the chat screen...
}
