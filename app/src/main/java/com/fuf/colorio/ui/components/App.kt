package com.fuf.colorio.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fuf.colorio.ui.theme.ColorioTheme

@Composable
fun App(){
    ColorioTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()

        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        }
    }

}

sealed class Routes(val name: String) {
    object Generator : Routes("generator")
    object Collection : Routes("collection")
    object Extractor : Routes("extractor")
}