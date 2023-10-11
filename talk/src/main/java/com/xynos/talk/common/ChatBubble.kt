package com.xynos.talk.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message
import com.xynos.talk.data.MessageStage
import com.xynos.talk.ui.theme.GrayBlue

@Composable
fun ChatBubble(
    message: Message,
    isMe: Boolean = false
) {
    val start = if (isMe) 80.dp else 0.dp
    val end = if (isMe) 0.dp else 80.dp
    val horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = start, end = end, top = 8.dp, bottom = 0.dp),
        horizontalArrangement = horizontalArrangement,
    ) {
        Row(

            modifier = Modifier
                .background(GrayBlue, RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.Bottom

            )
        {

            MessageText(message.text)
            TimeText(message.timestamp)
            Tick(message.stage == MessageStage.READ.value)
        }
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
        ChatBubble(message)
    }
}
