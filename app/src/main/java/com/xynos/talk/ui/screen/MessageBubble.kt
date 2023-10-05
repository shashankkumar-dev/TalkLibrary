package com.xynos.talk.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xynos.talk.data.Message
import com.xynos.talk.data.MessageStage
import com.xynos.talk.ui.common.MessageText
import com.xynos.talk.ui.common.Tick
import com.xynos.talk.ui.common.TimeText

@Composable
fun MessageBubble(
    message: Message,
) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column {
            MessageText(message.text)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TimeText(message.timestamp)
                Tick(message.stage == MessageStage.READ.value)
                Tick(message.stage == MessageStage.DELIVERED.value)
            }
        }
    }
}