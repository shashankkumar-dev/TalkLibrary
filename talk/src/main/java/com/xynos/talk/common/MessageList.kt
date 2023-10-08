package com.xynos.talk.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xynos.talk.data.Message

@Composable
fun MessageList(messages: List<Message>, sender: String, modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
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