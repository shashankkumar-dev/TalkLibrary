package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message

@Composable
fun ChatBubble(
    message: Message,
    imageUrl: String? = null,
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageBubble(imageUrl)
        MessageBubble(message = message)
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
    ChatBubble(
        message
    )
}
