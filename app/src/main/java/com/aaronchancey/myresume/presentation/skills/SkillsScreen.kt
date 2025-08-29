package com.aaronchancey.myresume.presentation.skills

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SkillsScreen(
    modifier: Modifier = Modifier,
    state: SkillsState,
) = Column(
    modifier = modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .verticalScroll(rememberScrollState()),
) {
    state.skills.forEach { skillGroup ->
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = skillGroup.title,
                    style = MaterialTheme.typography.titleLarge,
                )
                skillGroup.skills.forEach { skill ->
                    Text(text = "• $skill")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
