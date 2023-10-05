package com.xynos.talk.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


@Composable
fun SendButton() {
    Button(onClick = { /* Send message action */ }) {
        Icon(imageVector = Icons.Default.Send, contentDescription = "Send Message")
    }
}