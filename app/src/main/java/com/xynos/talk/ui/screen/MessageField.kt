package com.xynos.talk.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MessageField() {
    TextField(
        value = "", // Implement text state management
        onValueChange = { /* Update text state */ },
        placeholder = { Text(text = "Enter a message") }
    )
}