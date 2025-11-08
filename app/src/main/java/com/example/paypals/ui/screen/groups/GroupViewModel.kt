package com.example.paypals.ui.screen.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypals.R
import com.example.paypals.domain.model.Group
import com.example.paypals.domain.model.User
import com.example.paypals.domain.usecase.group.GroupUseCases
import com.example.paypals.domain.usecase.user.UserUseCases
import com.example.paypals.ui.components.EmptyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
     private val groupUseCase: GroupUseCases,
     private val userUseCase: UserUseCases
) : ViewModel() {

     private val _groups = MutableStateFlow<List<Group>>(emptyList())
     val


               groups: StateFlow<List<Group>> = _groups.asStateFlow()

     private val _users = MutableStateFlow<List<User>>(emptyList())
     val users: StateFlow<List<User>> = _users.asStateFlow()

     private val _error = MutableStateFlow<String?>(null)
     val error: StateFlow<String?> = _error.asStateFlow()

     val emptyState: StateFlow<EmptyState?> = _groups.map { userList ->
          if (userList.isEmpty()) {
               EmptyState(
                    imageRes = R.drawable.ghostnodatagroup,
                    message = "Parece que no hay Grupos todavía..."
               )
          } else {
               null
          }
     }.stateIn(viewModelScope, SharingStarted.Lazily, null)

     init {
          loadGroups()
          loadUsers()

     }



     private fun loadUsers(){
          viewModelScope.launch {
               try{
                    userUseCase.getAllUsers().collectLatest { userList ->
                         _users.value = userList
                    }
               } catch(e: Exception) {
                    _error.value = e.message ?: "Error loading users"
               }
          }
     }

     private fun loadGroups() {
          viewModelScope.launch {
               try {
                    groupUseCase.getAllGroups().collectLatest { groupList ->
                         _groups.value = groupList
                    }
               } catch (e: Exception) {
                    _error.value = e.message ?: "Error loading groups"
               }
          }
     }

     fun addGroup(group: Group) {
          viewModelScope.launch {
               try {
                    groupUseCase.addGroup(group)
                    loadGroups()  // recarga lista después de agregar
               } catch (e: Exception) {
                    _error.value = e.message ?: "Error adding group"
               }
          }
     }

     fun deleteGroup(groupId: Int) {
          viewModelScope.launch {
               try {
                    groupUseCase.deleteGroup(groupId)
                    loadGroups()  // recarga lista después de borrar
               } catch (e: Exception) {
                    _error.value = e.message ?: "Error deleting group"
               }
          }
     }
}
