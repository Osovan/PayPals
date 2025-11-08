package com.example.paypals.ui.screen.pay

import com.example.paypals.domain.model.Group
import com.example.paypals.domain.model.Payment
import com.example.paypals.domain.model.User

fun getNextUserToPay(group: Group?, payments: List<Payment>): User? {
     if (group == null || group.members.isEmpty()) return null

     // Mapear cuántos pagos hizo cada miembro
     val paymentCounts = payments.groupingBy { it.userId }.eachCount()

     return group.members.minWithOrNull(compareBy(
          { paymentCounts[it.id] ?: 0 }, // primero menor cantidad de pagos
          { it.name } // si hay empate, ordena por nombre (opcional)
     ))
}