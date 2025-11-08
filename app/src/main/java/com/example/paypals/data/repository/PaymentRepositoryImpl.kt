package com.example.paypals.data.repository

import com.example.paypals.data.local.dao.PaymentDao
import com.example.paypals.data.mapper.toDomain
import com.example.paypals.data.mapper.toEntity
import com.example.paypals.domain.model.Payment
import com.example.paypals.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
     private val paymentDao: PaymentDao,
) : PaymentRepository {

     override suspend fun addPayment(payment: Payment) {
          paymentDao.insertPayment(payment.toEntity())
     }

     override fun getPaymentsForGroup(groupId: Int): Flow<List<Payment>> {
          return paymentDao.getPaymentsByGroup(groupId).map { list ->
               list.map { it.toDomain() }
          }
     }
}