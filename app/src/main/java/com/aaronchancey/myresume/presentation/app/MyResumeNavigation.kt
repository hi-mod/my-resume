package com.aaronchancey.myresume.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.aaronchancey.myresume.presentation.developerprofile.DeveloperProfileScreen
import com.aaronchancey.myresume.presentation.experience.ExperienceDetailsScreen
import com.aaronchancey.myresume.presentation.experience.ExperienceScreen
import com.aaronchancey.myresume.presentation.experience.ExperienceViewModel
import com.aaronchancey.myresume.presentation.references.ReferencesScreen
import com.aaronchancey.myresume.presentation.references.ReferencesViewModel

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
        println("start of navhost")
        composable<Route.Profile> {
            DeveloperProfileScreen()
        }
        navigation<Route.Experience>(Route.ExperienceList) {
            composable<Route.ExperienceList> {
                val viewModel = it.sharedViewModel<ExperienceViewModel>(navController)
                val state by viewModel.experienceState.collectAsStateWithLifecycle()
                ExperienceScreen(
                    navController = navController,
                    state = state,
                )
            }
            composable<Route.ExperienceDetails>(
                deepLinks = listOf(
                    // Example: "myresume://experience/1"
                    navDeepLink {
                        uriPattern = "com.aaronchancey.myresume.presentation.app.Route.ExperienceDetails/{experienceId}"
                    },
                ),
            ) {
                val route = it.toRoute<Route.ExperienceDetails>()
                val viewModel = it.sharedViewModel<ExperienceViewModel>(navController)
                val state by viewModel.experienceState.collectAsStateWithLifecycle()
                println("savedStateHandle: ${it.savedStateHandle.get<Int>("experienceId")}")
                val experienceId = it.savedStateHandle.get<Int>("experienceId")
                // Fallback: If experienceId is still missing, navigate to experience list
                if (experienceId == null) {
                    println("experienceId missing, navigating to experience list")
                    navController.navigate(Route.ExperienceList::class.qualifiedName ?: "") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                    return@composable
                }
                val experienceEntryDetails = state.experienceEntries.firstOrNull { experienceEntry ->
                    experienceEntry.experienceId == experienceId
                }?.description
                if (experienceEntryDetails == null) {
                    println("Navigating up")
                    navController.navigateUp()
                    return@composable
                }
                ExperienceDetailsScreen(experienceEntryDetails = experienceEntryDetails)
            }
        }
        composable<Route.References> {
            val viewModel = hiltViewModel<ReferencesViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ReferencesScreen(state = state)
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(
        viewModelStoreOwner = parentEntry,
    )
}
