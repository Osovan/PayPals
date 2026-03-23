package com.example.paypals.ui.screen.pay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PaymentHistoryList(paymentsWithUserNames: List<PaymentWithUser>) {
     LazyColumn(
          modifier = Modifier
               .fillMaxWidth()
               .padding(top = 16.dp)
     ) {
          items(paymentsWithUserNames) { item ->
               PaymentHistoryItem(item)
               HorizontalDivider()
          }
     }
}

@Composable
fun PaymentHistoryItem(paymentWithUser: PaymentWithUser) {
     val formattedDate = remember(paymentWithUser.payment.timestamp) {
          val calendar = java.util.Calendar.getInstance()
          val today = calendar.clone() as java.util.Calendar

          calendar.time = java.util.Date(paymentWithUser.payment.timestamp)
          val paymentDay = calendar.clone() as java.util.Calendar

          fun isSameDay(c1: java.util.Calendar, c2: java.util.Calendar) =
               c1.get(java.util.Calendar.YEAR) == c2.get(java.util.Calendar.YEAR) &&
                       c1.get(java.util.Calendar.DAY_OF_YEAR) == c2.get(java.util.Calendar.DAY_OF_YEAR)

          val yesterday = today.clone() as java.util.Calendar
          yesterday.add(java.util.Calendar.DAY_OF_YEAR, -1)

          val dayLabel = when {
               isSameDay(paymentDay, today) -> "Hoy"
               isSameDay(paymentDay, yesterday) -> "Ayer"
               else -> {
                    val sdf = java.text.SimpleDateFormat("EEEE", java.util.Locale("es", "ES"))
                    sdf.format(paymentDay.time)
               }
          }

          val sdf = java.text.SimpleDateFormat("d 'de' MMMM 'de' yyyy 'a las' HH:mm", java.util.Locale("es", "ES"))
          "$dayLabel ${sdf.format(paymentDay.time)}"
     }

     Row(
          modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 8.dp, horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween
     ) {
          Column {
               Text(text = paymentWithUser.userName, style = MaterialTheme.typography.bodyLarge)
               Text(text = formattedDate, style = MaterialTheme.typography.bodySmall)
          }

          Text(
               text = "${paymentWithUser.payment.amount} €",
               style = MaterialTheme.typography.bodyLarge,
               modifier = Modifier.align(Alignment.CenterVertically)
          )
     }
}