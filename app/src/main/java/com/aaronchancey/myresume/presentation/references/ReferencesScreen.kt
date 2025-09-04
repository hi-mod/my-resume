package com.aaronchancey.myresume.presentation.references

import android.content.Intent
import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.aaronchancey.myresume.domain.Reference
import com.aaronchancey.myresume.presentation.components.LoadingIndicator

@Composable
fun ReferencesScreen(
    modifier: Modifier = Modifier,
    state: ReferencesState,
) = Column(
    modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    LoadingIndicator(
        loading = state.loading,
    ) {
        state.references.forEach { reference ->
            ReferenceItem(reference = reference)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ReferenceItem(
    modifier: Modifier = Modifier,
    reference: Reference,
) = Card(
    modifier = modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = reference.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier.clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:${reference.phoneNumber}".toUri()
                    }
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                },
            ),
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Default.Phone,
                contentDescription = null,
            )
            SelectionContainer {
                Text(text = PhoneNumberUtils.formatNumber(reference.phoneNumber, "US") ?: reference.phoneNumber)
            }
        }
        Row(
            modifier = Modifier.clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:".toUri() // Only email apps handle this.
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(reference.email))
                        putExtra(Intent.EXTRA_SUBJECT, "subject")
                    }
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                },
            ),
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Default.Email,
                contentDescription = null,
            )
            SelectionContainer {
                Text(text = reference.email)
            }
        }
    }
}
