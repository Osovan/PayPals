package com.example.paypals.domain.model

data class Group(
     val id: Int,
     val name: String,
     val createdAt: Long,
     val members: List<User>
)