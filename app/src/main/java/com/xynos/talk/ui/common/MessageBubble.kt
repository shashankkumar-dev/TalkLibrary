package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message
import com.xynos.talk.data.MessageStage

@Composable
fun MessageBubble(
    message: Message,
    isMe: Boolean = false,
) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {

        val horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start

        Column {
            MessageText(message.text, isMe)
            Row(
                horizontalArrangement = horizontalArrangement,

                modifier = Modifier.padding(8.dp)
            ) {
                TimeText(message.timestamp)
                Tick(message.stage == MessageStage.READ.value)
            }
        }
    }
}