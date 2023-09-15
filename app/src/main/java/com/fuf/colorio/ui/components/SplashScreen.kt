package com.fuf.colorio.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

const val SplashDelay = 2000L

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(SplashDelay)
        currentOnTimeout()
    }

    Image(
        Icons.Outlined.Add,
        contentDescription = null,
        modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun SplashPreview() {
    SplashScreen(
        modifier = Modifier.alpha(0.11f),
        onTimeout = {}
    )
}