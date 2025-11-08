package com.example.paypals.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GroupWithUsers(
     @Embedded val group: GroupEntity,
     @Relation(
          parentColumn = "id",   // PK de GroupEntity
          entityColumn = "id",   // PK de UserEntity
          associateBy = Junction(
               value = GroupUserCrossRef::class,
               parentColumn = "groupId",  // columna en GroupUserCrossRef que apunta a GroupEntity
               entityColumn = "userId"    // columna en GroupUserCrossRef que apunta a UserEntity
          )
     )
     val users: List<UserEntity>
)