package com.xynos.talk.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MessageText(message: String) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = message,
        style = TextStyle(color = Color.White),
        fontSize = 18.sp
    )
}
