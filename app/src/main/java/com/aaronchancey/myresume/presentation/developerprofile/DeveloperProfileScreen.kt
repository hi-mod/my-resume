package com.aaronchancey.myresume.presentation.developerprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aaronchancey.myresume.R
import com.aaronchancey.myresume.presentation.components.topAppBar

@Composable
fun DeveloperProfileScreen(
    navController: NavController,
) = Scaffold(
    topBar = topAppBar(
        title = R.string.profile,
        navController = navController,
        showBackButton = false,
    ),
) { padding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
    ) {
        Text(stringResource(R.string.profileStory))
    }
}
