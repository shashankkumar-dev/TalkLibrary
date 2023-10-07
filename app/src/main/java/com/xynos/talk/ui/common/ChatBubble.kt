package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message

@Composable
fun ChatBubble(
    message: Message,
    imageUrl: String? = null,
    isMe: Boolean = false
) {
    val start = if (isMe) 80.dp else 8.dp
    val end = if (isMe) 8.dp else 80.dp
    val horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .padding(start = start, end = end),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MessageBubble(message = message, isMe)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChatBubble() {
    val message = Message(
        id = "1",
        sender = "Sender",
        receiver = "Receiver",
        text = "Hello, how are you?",
        timestamp = System.currentTimeMillis(),
        chatId = ""
    )
    Column {
        ChatBubble(message, isMe = true)
        ChatBubble(message)
    }
}
