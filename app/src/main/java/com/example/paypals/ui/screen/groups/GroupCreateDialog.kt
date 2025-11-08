package com.example.paypals.ui.screen.groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.paypals.domain.model.User

@Composable
fun GroupCreateDialog(
     users: List<User>,
     onDismiss: () -> Unit,
     onCreateGroup: (String, List<User>) -> Unit,
) {
     var groupName by remember { mutableStateOf("") }
     val selectedUsers = remember { mutableStateListOf<User>() }

     AlertDialog(
          onDismissRequest = onDismiss,
          confirmButton = {
               TextButton(
                    onClick = {
                         onCreateGroup(groupName.trim(), selectedUsers.toList())
                         onDismiss()
                    },
                    enabled = groupName.isNotBlank()
               ) {
                    Text("Crear")
               }
          },
          dismissButton = {
               TextButton(onClick = onDismiss) {
                    Text("Cancelar")
               }
          },
          title = { Text("Nuevo grupo") },
          text = {
               Column {
                    OutlinedTextField(
                         value = groupName,
                         onValueChange = { groupName = it },
                         label = { Text("Nombre del grupo") },
                         modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Selecciona miembros:", style = MaterialTheme.typography.bodyMedium)

                    LazyColumn {
                         items(users.size) { index ->
                              val user = users[index]
                              val isSelected = user in selectedUsers

                              Row(
                                   modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .toggleable(
                                             value = isSelected,
                                             onValueChange = {
                                                  if (isSelected) selectedUsers.remove(user)
                                                  else selectedUsers.add(user)
                                             }
                                        )
                              ) {
                                   Checkbox(
                                        checked = isSelected,
                                        onCheckedChange = null // handled by toggleable
                                   )
                                   Spacer(modifier = Modifier.width(8.dp))
                                   Text(user.name)
                              }
                         }
                    }
               }
          }
     )
}
