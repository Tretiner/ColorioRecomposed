package com.fuf.colorio.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BottomNavBar(
    items: List<Route>,
    onItemSelected: (route: String) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, item.route) },
                label = { Text(stringResource(id = item.nameId)) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onItemSelected(items[index].route)
                }
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    BottomNavBar(
        items = listOf(MainRoute.Generator, MainRoute.Collection, MainRoute.Extractor),
        onItemSelected = {}
    )
}