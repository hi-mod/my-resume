package com.aaronchancey.myresume.presentation.experience

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aaronchancey.myresume.domain.ExperienceEntry
import com.aaronchancey.myresume.presentation.app.Route

@Composable
fun ExperienceScreen(
    modifier: Modifier = Modifier,
    state: ExperienceState,
    navController: NavController,
) = Column(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
) {
    state.experienceEntries.forEachIndexed { index, experienceEntry ->
        ExperienceEntryCard(
            modifier = Modifier.padding(bottom = if (index == state.experienceEntries.lastIndex) 0.dp else 16.dp),
            experienceEntry = experienceEntry,
            navController = navController,
        )
    }
}

@Composable
private fun ExperienceEntryCard(
    modifier: Modifier = Modifier,
    experienceEntry: ExperienceEntry,
    navController: NavController,
) = Card(
    modifier = modifier,
    onClick = {
        navController.navigate(Route.ExperienceDetails(experienceEntry.experienceId))
    },
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = experienceEntry.company,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = experienceEntry.role,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(text = experienceEntry.duration)
    }
}
