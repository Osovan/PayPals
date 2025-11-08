package com.example.paypals.domain.usecase.payment

data class PaymentUseCases(
     val addPayment: AddPaymentUseCase,
     val getPaymentsForGroup: GetPaymentsForGroupUseCase
)