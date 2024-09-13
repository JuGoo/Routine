package com.routine.app.route;

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.routine.app.screen.edit.EditScreenRoute

enum class Screen {
    EDIT
}

sealed class NavigationItem(val route: String) {
    data object Edit : NavigationItem(Screen.EDIT.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Edit.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Edit.route) {
            EditScreenRoute()
        }
    }
}