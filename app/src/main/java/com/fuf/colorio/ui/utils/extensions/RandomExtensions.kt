package com.fuf.colorio.ui.utils.extensions

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

object RandomExtensions {
    fun Random.nextColor() = Color(nextInt())
}