package com.xynos.talk.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ChatTileText(chatTitle: String, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = chatTitle,
        fontWeight = fontWeight,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}