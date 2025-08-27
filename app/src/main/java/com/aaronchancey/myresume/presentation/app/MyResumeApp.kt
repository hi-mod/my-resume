package com.aaronchancey.myresume.presentation.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aaronchancey.myresume.R
import com.aaronchancey.myresume.presentation.components.topAppBar

@Composable
fun MyResumeApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = topAppBar(
            title = when (currentRoute) {
                Route.Experience::class.qualifiedName -> R.string.experience
                Route.Profile::class.qualifiedName -> R.string.profile
                Route.References::class.qualifiedName -> R.string.references
                Route.Skills::class.qualifiedName -> R.string.skills
                else -> R.string.app_name
            },
            navController = navController,
            showBackButton = currentRoute != Route.Profile::class.qualifiedName,
        ),
        bottomBar = { ResumeNavigationBar(navController) },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            MyResumeNavigation(navController = navController)
        }
    }
}
