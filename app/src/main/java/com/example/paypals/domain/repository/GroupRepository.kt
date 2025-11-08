package com.example.paypals.domain.repository

import com.example.paypals.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
     fun getAllGroups(): Flow<List<Group>>
     suspend fun getGroupById(id: Int): Flow<Group?>
     suspend fun addGroup(group: Group): Int
     suspend fun deleteGroupById(groupId: Int)
}