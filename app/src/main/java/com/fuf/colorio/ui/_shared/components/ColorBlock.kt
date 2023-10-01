package com.fuf.colorio.ui._shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fuf.colorio.ui.utils.extensions.ColorExtensions.contentColorForBackground
import com.fuf.colorio.ui.utils.extensions.ColorExtensions.hex

private const val DEFAULT_DARK_MODE_COLOR: Long = 0xFF353535
private const val DEFAULT_LIGHT_MODE_COLOR: Long = 0xFFE5E5E5

@Composable
fun ColorBlock(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    color: Color,
    locked: Boolean,
    onClick: () -> Unit,
    onToggleLock: (Boolean) -> Unit
) {
    val realColor = remember(color, isDarkMode) {
        color.takeIf { it.isSpecified } ?: Color(
            when (isDarkMode) {
                true -> DEFAULT_DARK_MODE_COLOR
                false -> DEFAULT_LIGHT_MODE_COLOR
            }
        )
    }

    val contentColor = remember(realColor) {
        realColor.contentColorForBackground()
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(realColor)
        .clickable { onClick() }
    ) {
        Text(
            text = realColor.hex,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            color = contentColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        IconToggleButton(
            checked = locked,
            modifier = Modifier.align(Alignment.TopEnd),
            onCheckedChange = onToggleLock
        ) {
            Icon(
                if (locked) Icons.Outlined.Lock else Icons.Outlined.LockOpen,
                null,
                tint = contentColor
            )
        }
    }
}

@Preview(
    name = "ColorBlock",
    widthDp = 400, heightDp = 600,
    showSystemUi = true
)
@Composable
private fun PreviewColorBlock() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        (1..5).map {
            ColorBlock(
                modifier = Modifier.weight(1f),
                color = Color.Unspecified,
                locked = false,
                onClick = {},
                onToggleLock = {  },
            )
        }
    }
}