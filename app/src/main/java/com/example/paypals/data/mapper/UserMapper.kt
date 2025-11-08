package com.example.paypals.data.mapper

import com.example.paypals.data.local.entity.UserEntity
import com.example.paypals.domain.model.User

fun User.toEntity(): UserEntity {
     return UserEntity(
          id = this.id,
          name = this.name,
          profileImageUri = this.profileImageUri
     )
}

fun UserEntity.toDomain(): User {
     return User(
          id = this.id,
          name = this.name,
          profileImageUri = this.profileImageUri
     )
}