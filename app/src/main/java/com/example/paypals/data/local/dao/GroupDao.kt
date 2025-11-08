package com.example.paypals.data.local.dao

import androidx.room.*
import com.example.paypals.data.local.entity.GroupEntity
import com.example.paypals.data.local.entity.GroupUserCrossRef
import com.example.paypals.data.local.entity.GroupWithUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

     @Transaction
     @Query("SELECT * FROM groups")
     fun getAllGroupsWithUsers(): Flow<List<GroupWithUsers>>

     @Transaction
     @Query("SELECT * FROM groups WHERE id = :groupId")
     fun getGroupWithUsersById(groupId: Int): Flow<GroupWithUsers?>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertGroup(group: GroupEntity): Long

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertGroupUserCrossRefs(crossRefs: List<GroupUserCrossRef>)

     @Query("DELETE FROM groups WHERE id = :groupId")
     suspend fun deleteGroupById(groupId: Int)

     @Query("DELETE FROM GroupUserCrossRef WHERE groupId = :groupId")
     suspend fun deleteGroupMembers(groupId: Int)
}
