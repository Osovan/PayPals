package com.example.paypals.data.repository

import com.example.paypals.data.local.dao.GroupDao
import com.example.paypals.data.local.entity.GroupUserCrossRef
import com.example.paypals.data.mapper.toDomain
import com.example.paypals.data.mapper.toEntity
import com.example.paypals.domain.model.Group
import com.example.paypals.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepositoryImpl @Inject constructor(
     private val groupDao: GroupDao,
) : GroupRepository {

     override fun getAllGroups(): Flow<List<Group>> {
          return groupDao.getAllGroupsWithUsers().map { list -> list.map { it.toDomain() } }
     }

     override suspend fun getGroupById(id: Int): Flow<Group?> {
          return groupDao.getGroupWithUsersById(id).map { groupWithUsers ->
               groupWithUsers?.toDomain()
          }
     }

     override suspend fun addGroup(group: Group): Int {
          // 1. Insertar grupo
          val groupId = groupDao.insertGroup(group.toEntity()).toInt()

          // 2. Insertar relaciones usuario-grupo
          val crossRefs = group.members.map { user ->
               GroupUserCrossRef(groupId = groupId, userId = user.id)
          }
          groupDao.insertGroupUserCrossRefs(crossRefs)

          return groupId
     }

     override suspend fun deleteGroupById(groupId: Int) {
          groupDao.deleteGroupMembers(groupId)
          groupDao.deleteGroupById(groupId)
     }
}
