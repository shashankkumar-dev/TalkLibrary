package com.xynos.talk.common

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
import com.xynos.talk.data.ChatWithMessages

@Composable
fun ChatTile(
    chatWithMessages: ChatWithMessages,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageBubble(imageUrl = chatWithMessages.chat.photoUrl)
        Spacer(modifier = Modifier.width(8.dp))
        val lastMessage = chatWithMessages.messages.lastOrNull()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            ChatTileText(chatWithMessages.chat.user2, FontWeight.Bold)
            if (lastMessage != null) {
                ChatTileText(lastMessage.text)
            }

        }
        if (lastMessage != null) {
            TimeText(sentTime = lastMessage.timestamp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChatTile() {
    val url =
        "https://static.vecteezy.com/system/resources/previews/007/301/307/original/flat-cartoon-character-illustration-boy-people-icon-afro-man-portrait-avatar-head-indian-user-for-web-sites-and-applications-stock-design-vector.jpg"

    ChatTile(
        ChatWithMessages(
            chat = Chat(
                id = "1",
                user1 = "John Doe",
                user2 = "Jane Doe",
                photoUrl = url,
            ),
            messages = emptyList()
        )
    )
}
