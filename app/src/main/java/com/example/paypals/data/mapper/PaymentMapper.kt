package com.example.paypals.data.mapper

import com.example.paypals.data.local.entity.PaymentEntity
import com.example.paypals.domain.model.Payment

fun PaymentEntity.toDomain(): Payment = Payment(
     id = id,
     groupId = groupId,
     userId = userId,
     amount = amount,
     timestamp = timestamp
)

fun Payment.toEntity(): PaymentEntity = PaymentEntity(
     id = id,
     groupId = groupId,
     userId = userId,
     amount = amount,
     timestamp = timestamp
)
