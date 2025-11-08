package com.example.paypals.domain.usecase.payment

import com.example.paypals.domain.model.Payment
import com.example.paypals.domain.repository.PaymentRepository

class AddPaymentUseCase(private val repository: PaymentRepository) {
     suspend operator fun invoke(payment: Payment) = repository.addPayment(payment)
}