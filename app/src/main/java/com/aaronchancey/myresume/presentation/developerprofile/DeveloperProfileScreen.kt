package com.aaronchancey.myresume.presentation.developerprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeveloperProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileState,
) = Column(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    if (state.loading) {
        Spacer(modifier = Modifier.weight(1f))
        CircularProgressIndicator()
        Text("Loading...")
        Spacer(modifier = Modifier.weight(1f))
    } else {
        Text(state.profile)
    }
}
