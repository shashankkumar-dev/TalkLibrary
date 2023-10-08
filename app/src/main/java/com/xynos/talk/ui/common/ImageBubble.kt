package com.xynos.talk.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImageBubble(imageUrl: String?) {
    val url = "https://avatars.githubusercontent.com/u/5985097?v=4"
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
    )
}

@Preview

@Composable
fun ImageBubblePreview() {
    ImageBubble("https://avatars.githubusercontent.com/u/5985097?v=4")
}