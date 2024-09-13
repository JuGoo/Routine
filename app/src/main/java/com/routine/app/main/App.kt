package com.routine.app.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.routine.app.route.AppNavHost

@Composable
fun App() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}
