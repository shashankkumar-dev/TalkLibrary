package com.xynos.talk.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Chat
import com.xynos.talk.data.Message
import com.xynos.talk.ui.common.ImageBubble
import com.xynos.talk.ui.common.TimeText

@Composable
fun ChatTile(
    chat: Chat,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageBubble(imageUrl = chat.photoUrl)
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            ChatTileText(chat.user2, FontWeight.Bold)
            ChatTileText(chat.messages.last().text)
        }
        TimeText(sentTime = chat.messages.last().timestamp)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChatTile() {
    ChatTile(
        chat = Chat(
            id = "1",
            user1 = "John Doe",
            user2 = "Jane Doe",
            messages = listOf(
                Message(
                    id = "1",
                    text = "Hey! How are you?",
                    timestamp = System.currentTimeMillis(),
                    sender = "sender",
                    receiver = "receiver"
                )
            ),
            photoUrl = "https://example.com/path_to_image.jpg",
        )
    )
}
