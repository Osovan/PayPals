package com.example.paypals.ui.screen.users

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.paypals.domain.model.User

@Composable
fun EditUserDialog(
     user: User,
     onDismiss: () -> Unit,
     onConfirm: (String, Uri?) -> Unit
) {
     var name by remember { mutableStateOf(user.name) }
     var newImageUri by remember { mutableStateOf<Uri?>(null) }

     val context = LocalContext.current

     val imagePicker = rememberLauncherForActivityResult(
          contract = ActivityResultContracts.OpenDocument()
     ) { uri ->
          uri?.let {
               context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
               )
               newImageUri = it
          }
     }

     AlertDialog(
          onDismissRequest = onDismiss,
          title = { Text("Editar usuario") },
          text = {
               Column {
                    Box(
                         modifier = Modifier
                              .size(96.dp)
                              .clip(CircleShape)
                              .background(Color.LightGray)
                              .clickable { imagePicker.launch(arrayOf("image/*")) },
                         contentAlignment = Alignment.Center
                    ) {
                         val img = newImageUri ?: user.profileImageUri.takeIf { it.isNotEmpty() }?.let { Uri.parse(it) }

                         if (img != null) {
                              AsyncImage(
                                   model = img,
                                   contentDescription = null,
                                   modifier = Modifier.fillMaxSize(),
                                   contentScale = ContentScale.Crop
                              )
                         } else {
                              Icon(
                                   imageVector = Icons.Default.Person,
                                   contentDescription = null,
                                   tint = Color.White,
                                   modifier = Modifier.size(48.dp)
                              )
                         }
                    }

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                         value = name,
                         onValueChange = { name = it },
                         label = { Text("Nombre") },
                         modifier = Modifier.fillMaxWidth()
                    )
               }
          },
          confirmButton = {
               Button(
                    onClick = {
                         onConfirm(name, newImageUri)
                    }
               ) { Text("Guardar") }
          },
          dismissButton = {
               Button(onClick = onDismiss) { Text("Cancelar") }
          }
     )
}