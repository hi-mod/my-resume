package com.aaronchancey.myresume.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.LoadingIndicator(
    loading: Boolean,
    content: @Composable () -> Unit,
) {
    if (loading) {
        Spacer(modifier = Modifier.weight(1f))
        CircularProgressIndicator()
        Text("Loading...")
        Spacer(modifier = Modifier.weight(1f))
    } else {
        content()
    }
}
