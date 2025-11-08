package com.example.paypals.domain.usecase.payment

import com.example.paypals.domain.model.Payment
import com.example.paypals.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow

class GetPaymentsForGroupUseCase(private val repository: PaymentRepository) {
     operator fun invoke(groupId: Int): Flow<List<Payment>> = repository.getPaymentsForGroup(groupId)
}