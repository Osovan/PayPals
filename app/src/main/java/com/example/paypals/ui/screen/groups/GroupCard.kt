package com.example.paypals.ui.screen.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.paypals.domain.model.Group
import com.example.paypals.utils.profileColors

@Composable
fun GroupCard(
     group: Group,
     onEdit: () -> Unit,
     onDelete: () -> Unit,
     modifier: Modifier
) {
     Card(
          modifier = modifier
               .fillMaxWidth()
               .height(72.dp)
               .padding(vertical = 8.dp),
          shape = RoundedCornerShape(12.dp),
          elevation = CardDefaults.cardElevation(6.dp),
          colors = CardDefaults.cardColors(
               containerColor = MaterialTheme.colorScheme.surfaceVariant
          )
     ) {
          Row(
               modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween
          ) {
               Column {
                    Text(
                         text = group.name,
                         style = MaterialTheme.typography.titleMedium,
                         color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                         text = "${group.members.size} miembros",
                         style = MaterialTheme.typography.bodyMedium,
                         color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
               }
               Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
               ) {

                    IconButton(onClick = onEdit) {
                         Icon(
                              imageVector = Icons.Default.Edit,
                              contentDescription = "Editar grupo",
                              tint = MaterialTheme.colorScheme.secondary
                         )
                    }
                    IconButton(onClick = onDelete) {
                         Icon(
                              imageVector = Icons.Default.Delete,
                              contentDescription = "Eliminar grupo",
                              tint = MaterialTheme.colorScheme.secondary
                         )
                    }
               }
          }
     }
}