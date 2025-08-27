package com.aaronchancey.myresume.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aaronchancey.myresume.presentation.developerprofile.DeveloperProfileScreen

@Composable
fun MyResumeNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Profile,
    ) {
        composable<Route.Profile> {
            DeveloperProfileScreen(navController)
        }
    }
}
