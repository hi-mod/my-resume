package com.aaronchancey.myresume.presentation.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aaronchancey.myresume.R

@Composable
fun ResumeNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        R.string.profile,
        R.string.experience,
        R.string.references,
        R.string.skills,
    )
    val navRoutes = listOf(
        Route.Profile,
        Route.ExperienceList,
        Route.References,
        Route.Skills,
    )
    val icons = listOf(
        Icons.Default.TextFields,
        Icons.Default.WorkHistory,
        Icons.Default.People,
        Icons.Default.DownhillSkiing,
    )
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = "",
                    )
                },
                label = { Text(stringResource(id = item)) },
                onClick = {
                    selectedItem = index
                    navController.navigate(navRoutes[index])
                },
                selected = selectedItem == index,
            )
        }
    }
}
