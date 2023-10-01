package com.fuf.colorio.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fuf.colorio.ui.navigation.BottomNavBar
import com.fuf.colorio.ui.navigation.MainRoute
import com.fuf.colorio.ui.screens.collection.CollectionScreen
import com.fuf.colorio.ui.screens.extractor.ExtractorScreen
import com.fuf.colorio.ui.screens.generator.GeneratorScreen
import com.fuf.colorio.ui.theme.ColorioTheme

@Composable
fun App() {
    ColorioTheme {
        AppContent()
    }
}

val navRoutes = listOf(
    MainRoute.Generator,
    MainRoute.Collection,
    MainRoute.Extractor
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavBar(
                items = navRoutes,
                onItemSelected = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainRoute.Generator.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainRoute.Generator.route) { GeneratorScreen() }
            composable(MainRoute.Collection.route) { CollectionScreen() }
            composable(MainRoute.Extractor.route) { ExtractorScreen() }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() = App()