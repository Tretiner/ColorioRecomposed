package com.fuf.colorio.ui.utils

import androidx.compose.foundation.background
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

object ModifierUtils {
    fun Modifier.randomBackground(): Modifier = composed {
        val randomColorInt = rememberSaveable { Random.nextInt() }
        val randomColor = remember { Color(randomColorInt) }
        then(background(randomColor))
    }
}