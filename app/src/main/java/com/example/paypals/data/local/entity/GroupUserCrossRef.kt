package com.example.paypals.data.local.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["groupId", "userId"],
     indices = [
          Index(value = ["groupId"]),
          Index(value = ["userId"])
     ])
data class GroupUserCrossRef(
     val groupId: Int,
     val userId: Int
)