package com.example.paypals.ui.screen.groups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.paypals.domain.model.Group

@Composable
fun GroupList(
     groups: List<Group>,
     onDelete: (Group) -> Unit,
     modifier: Modifier = Modifier,
) {
     Column(modifier = modifier.padding(16.dp)) {
          if (groups.isEmpty()) {
               // Aquí podrías poner un mensaje de "No hay grupos"
          } else {
               for (group in groups) {
                    GroupCard(
                         group = group,
                         onDelete = { onDelete(group) },
                         onEdit = {},
                         modifier = Modifier.fillMaxWidth()
                    )
               }
          }
     }
}
