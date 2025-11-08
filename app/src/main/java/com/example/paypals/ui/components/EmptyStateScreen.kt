package com.example.paypals.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

data class EmptyState(
     val imageRes: Int,
     val message: String
)

@Composable
fun EmptyStateScreen(emptyState: EmptyState?, modifier: Modifier = Modifier) {
     if (emptyState == null) return

     Column(
          modifier = modifier.fillMaxWidth().padding(top = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally
     ) {
          Image(
               painter = painterResource(emptyState.imageRes),
               contentDescription = null,
               modifier = Modifier.size(200.dp)
          )
          Spacer(Modifier.height(16.dp))
          Text(
               text = emptyState.message,
               style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic),
               color = MaterialTheme.colorScheme.onSurfaceVariant
          )
     }
}
