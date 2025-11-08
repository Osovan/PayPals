package com.example.paypals.ui.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypals.R
import com.example.paypals.domain.model.User
import com.example.paypals.domain.usecase.user.UserUseCases
import com.example.paypals.ui.components.EmptyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
     private val userUseCases: UserUseCases,
) : ViewModel() {

     private val _users = MutableStateFlow<List<User>>(emptyList())
     val users: StateFlow<List<User>> = _users

     val emptyState: StateFlow<EmptyState?> = _users.map { userList ->
          if (userList.isEmpty()) {
               EmptyState(
                    imageRes = R.drawable.ghostnodata,
                    message = "¡Boo! No hay usuarios todavía..."
               )
          } else {
               null
          }
     }.stateIn(viewModelScope, SharingStarted.Lazily, null)

     init {
          viewModelScope.launch {
               userUseCases.getAllUsers().collect {
                    _users.value = it
               }
          }
     }

     fun addUser(name: String, profileUri: String) {
          viewModelScope.launch {
               userUseCases.addUser(
                    User(name = name, profileImageUri = profileUri)
               )
          }
     }

     fun deleteUser(userId: Int) {
          viewModelScope.launch {
               userUseCases.deleteUser(userId)
          }
     }
}