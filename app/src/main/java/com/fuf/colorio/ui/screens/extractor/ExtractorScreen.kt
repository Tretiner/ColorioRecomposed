package com.fuf.colorio.ui.screens.extractor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExtractorScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier.background(MaterialTheme.colorScheme.background)) {
        Text(text = "ExtractorScreen", color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(name = "ExtractorScreen")
@Composable
private fun PreviewExtractorScreen() {
    ExtractorScreen()
}