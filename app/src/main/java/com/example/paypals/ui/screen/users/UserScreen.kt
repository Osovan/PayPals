package com.example.paypals.ui.screen.users

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.paypals.domain.model.User
import com.example.paypals.ui.components.ConfirmDeleteDialog
import com.example.paypals.ui.components.EmptyStateScreen
import com.example.paypals.utils.getFileNameFromUri
import com.example.paypals.utils.getInitialAndColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserScreen(
     userViewModel: UserViewModel = hiltViewModel(),
) {
     var userToDelete by remember { mutableStateOf<User?>(null) }

     val listState = rememberLazyListState()
     var shouldScrollToEnd by remember { mutableStateOf(false) }

     val keyboardController = LocalSoftwareKeyboardController.current
     var selectedFileName by remember { mutableStateOf<String?>(null) }

     val users by userViewModel.users.collectAsState()
     var newUserName by remember { mutableStateOf("") }
     var userToEdit by remember { mutableStateOf<User?>(null) }

     val context = LocalContext.current
     var profileImageUri by remember { mutableStateOf<Uri?>(null) }

     val emptyState by userViewModel.emptyState.collectAsState()

     val imagePickerLauncher = rememberLauncherForActivityResult(
          contract = ActivityResultContracts.OpenDocument()
     ) { uri: Uri? ->
          Log.d("IMG_DEBUG", "URI recibido: $uri")
          profileImageUri = uri
          selectedFileName = uri?.let { getFileNameFromUri(context, it) }

          uri?.let {
               try {
                    context.contentResolver.takePersistableUriPermission(
                         it,
                         Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    Log.d("IMG_DEBUG", "Permiso persistente tomando OK para $it")
               } catch (e: Exception) {
                    Log.e("IMG_DEBUG", "ERROR al tomar permiso persistente", e)
               }
          }
     }

     LaunchedEffect(shouldScrollToEnd) {
          if (shouldScrollToEnd && users.isNotEmpty()) {
               listState.animateScrollToItem(users.lastIndex)
               shouldScrollToEnd = false
          }
     }

     Column(
          modifier = Modifier
               .fillMaxSize()
               .padding(16.dp)
     ) {
          Text(
               text = "Usuarios",
               style = MaterialTheme.typography.headlineLarge,
               modifier = Modifier.padding(bottom = 16.dp)
          )

          Spacer(modifier = Modifier.height(16.dp))

          Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
          ) {
               Box(
                    modifier = Modifier
                         .size(72.dp)
                         .clickable { imagePickerLauncher.launch(arrayOf("image/*")) },
                    contentAlignment = Alignment.Center
               ) {
                    if (profileImageUri != null) {
                         AsyncImage(
                              model = profileImageUri,
                              contentDescription = "Foto de perfil",
                              contentScale = ContentScale.Crop,
                              modifier = Modifier
                                   .size(72.dp)
                                   .clip(CircleShape)
                                   .background(Color.LightGray)
                         )
                    } else {
                         Box(
                              modifier = Modifier
                                   .size(72.dp)
                                   .clip(CircleShape)
                                   .background(Color.LightGray),
                              contentAlignment = Alignment.Center
                         ) {
                              Icon(
                                   imageVector = Icons.Default.Person,
                                   contentDescription = "Icono de usuario por defecto",
                                   tint = Color.White,
                                   modifier = Modifier.size(42.dp)
                              )
                         }
                    }

                    // Icono de cámara fuera del círculo
                    Icon(
                         imageVector = Icons.Default.Image,
                         contentDescription = "Seleccionar imagen",
                         tint = Color.White,
                         modifier = Modifier
                              .size(20.dp)
                              .align(Alignment.BottomEnd)
                              .background(MaterialTheme.colorScheme.primary, CircleShape)
                              .padding(4.dp)
                    )
               }

               Spacer(modifier = Modifier.width(16.dp))

               OutlinedTextField(
                    value = newUserName,
                    onValueChange = { newUserName = it },
                    label = { Text("Nombre del usuario") },
                    modifier = Modifier.weight(1f)
               )
          }

          Button(
               onClick = {
                    if (newUserName.isNotBlank()) {
                         userViewModel.addUser(
                              name = newUserName,
                              profileUri = profileImageUri?.toString() ?: ""
                         )
                         newUserName = ""
                         profileImageUri = null
                         selectedFileName = null
                         keyboardController?.hide()
                         shouldScrollToEnd = true
                    }
               },
               modifier = Modifier.align(Alignment.End)
          ) {
               Icon(
                    Icons.Default.PersonAdd,
                    contentDescription = "Añadir",
                    modifier = Modifier.size(18.dp)
               )
               Spacer(modifier = Modifier.width(8.dp))
               Text("Añadir usuario")
          }

          Spacer(modifier = Modifier.height(24.dp))

          LazyColumn(state = listState) {
               if (users.isEmpty()) {
                    item {

                         EmptyStateScreen(emptyState)
                    }
               } else {
                    items(users) { user ->
                         Row(
                              verticalAlignment = Alignment.CenterVertically,
                              modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(vertical = 8.dp)
                                   .combinedClickable(
                                        onClick = {},
                                        onLongClick = {
                                             userToEdit = user
                                        }
                                   )
                         ) {
                              val imageUri = user.profileImageUri
                              if (imageUri.isNotBlank()) {
                                   AsyncImage(
                                        model = imageUri,
                                        contentDescription = "Foto de perfil",
                                        modifier = Modifier
                                             .size(64.dp)
                                             .clip(CircleShape)
                                             .background(Color.LightGray),
                                        contentScale = ContentScale.Crop
                                   )
                              } else {
                                   val (initial, bgColor) = getInitialAndColor(user.name)

                                   Box(
                                        modifier = Modifier
                                             .size(64.dp)
                                             .clip(CircleShape)
                                             .background(bgColor),
                                        contentAlignment = Alignment.Center
                                   ) {
                                        Text(
                                             text = initial.toString(),
                                             color = Color.White,
                                             fontSize = 32.sp,
                                        )
                                   }
                              }

                              Spacer(modifier = Modifier.width(16.dp))

                              Text(
                                   text = user.name,
                                   style = MaterialTheme.typography.titleMedium,
                                   fontSize = 20.sp,
                                   modifier = Modifier.weight(1f)
                              )
                              IconButton(
                                   onClick = {
                                        userToDelete = user
                                   }
                              ) {
                                   Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Borrar usuario",
                                        tint = MaterialTheme.colorScheme.secondary
                                   )
                              }
                         }

                         HorizontalDivider()
                    }
               }
          }
     }

     if (userToDelete != null) {
          ConfirmDeleteDialog(
               title = "Confirmar borrado",
               message = "¿Estás seguro de que quieres borrar a ${userToDelete?.name}?",
               onConfirm = {
                    userViewModel.deleteUser(userToDelete!!.id)
                    userToDelete = null
               },
               onDismiss = { userToDelete = null }
          )
     }

     if (userToEdit != null) {
          EditUserDialog(
               user = userToEdit!!,
               onDismiss = { userToEdit = null },
               onConfirm = { newName, newUri ->
                    userViewModel.updateUser(
                         id = userToEdit!!.id,
                         name = newName,
                         profileUri = newUri?.toString() ?: userToEdit!!.profileImageUri
                    )
                    userToEdit = null
               }
          )
     }
}