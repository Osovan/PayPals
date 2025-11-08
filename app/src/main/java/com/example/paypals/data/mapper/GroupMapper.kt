package com.example.paypals.data.mapper

import com.example.paypals.data.local.entity.GroupEntity
import com.example.paypals.data.local.entity.GroupWithUsers
import com.example.paypals.domain.model.Group

// Mapear GroupWithUsers a Group (dominio)
fun GroupWithUsers.toDomain(): Group = Group(
     id = group.id,
     name = group.name,
     createdAt = group.createdAt,
     members = users.map { it.toDomain() }  // Usa el mapper UserEntity.toDomain()
)

// Mapear GroupEntity a Group (dominio) — sin usuarios
fun GroupEntity.toDomain(): Group = Group(
     id = id,
     name = name,
     createdAt = createdAt,
     members = emptyList()  // Como no hay usuarios aquí
)

// Mapear Group (dominio) a GroupEntity
fun Group.toEntity(): GroupEntity = GroupEntity(
     id = id,
     name = name,
     createdAt = createdAt
)
