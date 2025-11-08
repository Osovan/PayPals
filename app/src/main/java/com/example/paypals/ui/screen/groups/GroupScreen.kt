package com.example.paypals.ui.screen.groups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.paypals.domain.model.Group
import com.example.paypals.ui.components.ConfirmDeleteDialog
import com.example.paypals.ui.components.EmptyStateScreen

@Composable
fun GroupScreen(
     viewModel: GroupViewModel = hiltViewModel(),
) {
     val groups by viewModel.groups.collectAsState()
     val users by viewModel.users.collectAsState()
     val emptyState by viewModel.emptyState.collectAsState()

     var showCreateDialog by remember { mutableStateOf(false) }
     var groupToDelete by remember { mutableStateOf<Group?>(null) }

     Scaffold(
          floatingActionButton = {
               FloatingActionButton(onClick = { showCreateDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Nuevo grupo")
               }
          }
     ) { padding ->

          Column(
               modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
          ) {
               Text(
                    text = "Grupos",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
               )

               if (emptyState != null) {

                    Box(
                         modifier = Modifier
                              .padding(padding)
                              .fillMaxSize(),
                         contentAlignment = Alignment.Center
                    ) {
                         EmptyStateScreen(emptyState = emptyState!!)
                    }
               } else {
                    Column(
                         modifier = Modifier
                              .padding(padding)
                              .fillMaxSize()
                    ) {
                         GroupList(
                              groups = groups,
                              onDelete = { groupToDelete = it }
                         )
                    }
               }
          }

          if (showCreateDialog) {
               GroupCreateDialog(
                    users = users,
                    onDismiss = { showCreateDialog = false },
                    onCreateGroup = { name, selectedUsers ->
                         viewModel.addGroup(
                              Group(
                                   id = 0,
                                   name = name,
                                   createdAt = System.currentTimeMillis(),
                                   members = selectedUsers
                              )
                         )
                    }
               )
          }

          groupToDelete?.let { group ->
               ConfirmDeleteDialog(
                    title = "Eliminar grupo",
                    message = "¿Seguro que quieres eliminar el grupo \"${group.name}\"?",
                    onConfirm = {
                         viewModel.deleteGroup(group.id)
                         groupToDelete = null
                    },
                    onDismiss = { groupToDelete = null }
               )
          }
     }
}

