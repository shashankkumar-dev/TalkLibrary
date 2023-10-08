package com.xynos.talk.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xynos.talk.ui.theme.Blue
import com.xynos.talk.ui.theme.LightBlue


@Composable
fun MessageText(message: String, isMe: Boolean = false) {
    Text(
        modifier = Modifier
            .background(if (isMe) Blue else LightBlue, RoundedCornerShape(8.dp))
            .padding(8.dp),
        text = message,
        style = TextStyle(color = if (isMe) Color.White else Color.DarkGray),
        fontSize = 18.sp
    )
}
