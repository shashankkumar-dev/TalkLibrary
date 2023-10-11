package com.xynos.talk.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xynos.talk.navigation.NavGraph
import com.xynos.talk.ui.theme.TalkTheme

@Composable
fun MainScreen() {
    TalkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            NavGraph()
        }
    }
}