package com.xynos.talk.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xynos.talk.data.User
import com.xynos.talk.ui.common.ImageBubble
import com.xynos.talk.ui.navigation.Screen
import com.xynos.talk.ui.viewmodel.UserSearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSearchScreen(
    navController: NavController,
    viewModel: UserSearchViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val users by viewModel.searchResult.collectAsState()

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchUser(searchQuery)
            },
            label = { Text(text = "Search users") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(users.size) { index ->
                UserItem(user = users[index], viewModel, navController)
            }
        }
    }
}


@Composable
fun UserItem(user: User, viewModel: UserSearchViewModel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                viewModel.selectUser(user) { id ->
                    navController.navigate(Screen.ChatScreen.route(id))
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageBubble(imageUrl = user.photoUrl)

        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = user.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = user.id, style = MaterialTheme.typography.labelSmall)
        }
    }
}
