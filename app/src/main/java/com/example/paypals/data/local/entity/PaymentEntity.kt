package com.example.paypals.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentEntity(
     @PrimaryKey(autoGenerate = true) val id: Int = 0,
     val groupId: Int,
     val userId: Int,
     val amount: Double,
     val timestamp: Long
)
