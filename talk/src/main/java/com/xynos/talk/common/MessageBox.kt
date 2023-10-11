package com.xynos.talk.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xynos.talk.ui.theme.GrayBlue

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun MessageBox(onClick: (String) -> Unit) {
    var message by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = message,
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        onValueChange = { newValue -> message = newValue },
        placeholder = { Text(text = "Enter a message") },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = GrayBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Message",
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        if (message.isNotBlank()) {
                            keyboardController?.hide()
                            onClick(message)
                            message = ""
                        }
                    }
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun PreviewMessageBox() {
    MessageBox({})
}