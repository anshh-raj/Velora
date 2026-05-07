package com.example.velora.velora.presentation.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.velora.velora.presentation.route.NavRoute

val LocalNavController = compositionLocalOf<NavController>{ error("No NavController Found") }

@Composable
fun AppRouting(startDestination: NavRoute, route: String){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val rootNavController = rememberNavController()
        LaunchedEffect(key1 = route) {
            if(route != ""){
                rootNavController.navigate(route)
            }
        }

        CompositionLocalProvider(LocalNavController provides rootNavController) {
            Box(modifier = Modifier.fillMaxSize()){

            }
        }

    }
}