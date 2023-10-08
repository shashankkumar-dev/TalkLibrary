package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.ui.theme.Blue
import com.xynos.talk.ui.theme.VeryLightBlue

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun MessageBox(onClick: (String) -> Unit) {
    var message by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = { newValue -> message = newValue },
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = "Enter a message") },
            shape = RoundedCornerShape(12.dp),  // Rounded corners with 12.dp radius
            colors = TextFieldDefaults.textFieldColors(
                containerColor = VeryLightBlue, // Replace with your desired background color
                focusedIndicatorColor = Color.Transparent,  // Hide the bottom border when focused
                unfocusedIndicatorColor = Color.Transparent,  // Hide the bottom border when unfocused
                cursorColor = Blue,  // Replace with your desired cursor color
                textColor = Blue // Replace with your desired text color
            )
        )
        Button(
            onClick = {
                if (message.isNotBlank()) {
                    keyboardController?.hide()
                    onClick(message)
                    message = ""
                }
            },
            colors = ButtonDefaults.buttonColors(VeryLightBlue),
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Message",
                tint = Color.DarkGray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewMessageBox() {
    MessageBox({})
}