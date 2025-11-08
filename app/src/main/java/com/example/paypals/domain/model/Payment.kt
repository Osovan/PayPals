package com.example.paypals.domain.model

data class Payment(
     val id: Int = 0,
     val groupId: Int,
     val userId: Int,
     val amount: Double,
     val timestamp: Long
)