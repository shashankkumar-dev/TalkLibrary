package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Tick(isRead: Boolean) {
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = null,
        tint = if (isRead) Color.Blue else Color.Gray,
        modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
    )
}