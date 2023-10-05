package com.xynos.talk.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImageBubble(imageUrl: String?) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}