package com.fuf.colorio.ui.utils.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import kotlin.math.pow

object ColorExtensions {
    val Color.hex: String
        get() = "#%06X".format(0xFFFFFF and toArgb())

    fun Color.contentColorForBackground(): Color = when (canContainLightColor()) {
        true -> Color.Black
        false -> Color.White
    }

    fun Color.contentColorForBackgroundFancy(): Color = when (canContainLightColorFancy()) {
        true -> Color.Black
        false -> Color.White
    }

    fun Color.canContainLightColorFancy(): Boolean {
        val colorInt = toArgb()

        val uiColors = listOf(colorInt.red / 255.0, colorInt.green / 255.0, colorInt.blue / 255.0)

        val c = uiColors.map { col ->
            if (col <= 0.03928)
                col / 12.92
            else
                ((col + 0.055) / 1.055).pow(2.4)
        }

        return (0.2126 * c[0]) + (0.7152 * c[1]) + (0.0722 * c[2]) > 0.170 // default 0.179
    }

    /**
     * Determines if it is possible to use light colors on this background without eyes bleeding
     *
     * @see <a href="https://stackoverflow.com/a/3943023/17059038">Color formula</a>
     */
    fun Color.canContainLightColor(): Boolean {
        val colorInt = toArgb()

        println("$colorInt: ${colorInt.red} ${colorInt.green} ${colorInt.blue}")

        return colorInt.red * 0.299 + colorInt.green * 0.587 + colorInt.blue * 0.114 > 186
    }
}