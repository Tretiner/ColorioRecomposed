package com.fuf.colorio.ui.screens.collection

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "CollectionScreen")
    }
}

@Preview(name = "CollectionScreen")
@Composable
private fun PreviewCollectionScreen() {
    CollectionScreen()
}