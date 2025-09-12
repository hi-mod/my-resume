package com.aaronchancey.myresume.presentation.app

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aaronchancey.myresume.R

@Composable
fun ResumeNavigationBar(navController: NavController) {
    val topLevelRoutes = listOf(
        TopLevelRoute(
            name = R.string.profile,
            route = Route.Profile,
            icon = Icons.Default.TextFields,
        ),
        TopLevelRoute(
            name = R.string.experience,
            route = Route.ExperienceList,
            icon = Icons.Default.WorkHistory,
        ),
        TopLevelRoute(
            name = R.string.references,
            route = Route.References,
            icon = Icons.Default.People,
        ),
        TopLevelRoute(
            name = R.string.skills,
            route = Route.Skills,
            icon = Icons.Default.AccountTree,
        ),
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelRoutes.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                    )
                },
                label = { Text(stringResource(id = item.name)) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
            )
        }
    }
}

private data class TopLevelRoute<T : Route>(
    @param:StringRes val name: Int,
    val route: T,
    val icon: ImageVector,
)
