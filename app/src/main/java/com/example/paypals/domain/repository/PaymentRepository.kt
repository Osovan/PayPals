package com.example.paypals.domain.repository

import com.example.paypals.domain.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
     suspend fun addPayment(payment: Payment)
     fun getPaymentsForGroup(groupId: Int): Flow<List<Payment>>
}