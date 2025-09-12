package com.aaronchancey.myresume.presentation.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aaronchancey.myresume.R
import com.aaronchancey.myresume.presentation.components.topAppBar

@Composable
fun MyResumeApp(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = topAppBar(
            title = when {
                currentRoute == Route.ExperienceList::class.qualifiedName -> R.string.experience
                currentRoute == Route.Profile::class.qualifiedName -> R.string.profile
                currentRoute == Route.References::class.qualifiedName -> R.string.references
                currentRoute == Route.Skills::class.qualifiedName -> R.string.skills
                currentRoute?.startsWith(Route.ExperienceDetails::class.qualifiedName ?: "") == true -> R.string.experienceDetails
                else -> R.string.app_name
            },
            navController = navHostController,
            showBackButton = !topLevelRoutes.contains(currentRoute),
        ),
        bottomBar = {
            if (topLevelRoutes.contains(currentRoute)) {
                ResumeNavigationBar(navHostController)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            MyResumeNavigation(navController = navHostController)
        }
    }
}

private val topLevelRoutes = arrayOf(
    Route.Profile::class.qualifiedName,
    Route.Skills::class.qualifiedName,
    Route.References::class.qualifiedName,
    Route.ExperienceList::class.qualifiedName,
)
