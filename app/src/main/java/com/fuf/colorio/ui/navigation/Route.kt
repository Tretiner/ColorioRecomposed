package com.fuf.colorio.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.DataArray
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.ui.graphics.vector.ImageVector
import com.fuf.colorio.R

sealed class Route(
    open val route: String,
    open val icon: ImageVector,
    @StringRes open val nameId: Int
)

sealed class MainRoute(
    override val route: String,
    override val icon: ImageVector,
    @StringRes override val nameId:  Int
) : Route(route, icon, nameId) {
    object Generator : Route("generator", Icons.Outlined.Palette, R.string.generator)
    object Collection : Route("collection", Icons.Outlined.DataArray, R.string.collection)
    object Extractor : Route("extractor", Icons.Outlined.AddPhotoAlternate, R.string.extractor)
}