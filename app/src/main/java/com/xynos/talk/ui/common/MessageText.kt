package com.xynos.talk.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun MessageText(message: String) {
    Text(text = message, style = TextStyle(color = Color.Black))
}
