package com.example.paypals.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDeleteDialog(
     title: String,
     message: String,
     onConfirm: () -> Unit,
     onDismiss: () -> Unit,
     confirmText: String = "Borrar",
     dismissText: String = "Cancelar"
) {
     AlertDialog(
          onDismissRequest = onDismiss,
          title = { Text(title) },
          text = { Text(message) },
          confirmButton = {
               Button(onClick = onConfirm) {
                    Text(confirmText)
               }
          },
          dismissButton = {
               Button(onClick = onDismiss) {
                    Text(dismissText)
               }
          }
     )
}