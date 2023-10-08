package com.xynos.talk.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.xynos.talk.ui.theme.VeryLightBlue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ChatTopBar(imageUrl: String?, chatTitle: String) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ImageBubble(imageUrl = imageUrl)
                Text(chatTitle)
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Options"
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = VeryLightBlue,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        )
    )
}