package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message

@Composable
fun MessageList(messages: List<Message>, sender: String) {
    LazyColumn(
        modifier = Modifier.padding(top = 70.dp),
    ) {
        itemsIndexed(messages) { _, message ->
            val isMe = sender == message.sender
            ChatBubble(
                message = message,
                isMe
            )
        }
    }
}