package com.xynos.talk.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xynos.talk.ui.theme.GrayBlue

@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = GrayBlue
        )
    }
}