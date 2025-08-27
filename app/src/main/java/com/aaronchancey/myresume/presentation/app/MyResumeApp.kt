package com.aaronchancey.myresume.presentation.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyResumeApp() {
    val navController = rememberNavController()
    MyResumeNavigation(navController = navController)
}
