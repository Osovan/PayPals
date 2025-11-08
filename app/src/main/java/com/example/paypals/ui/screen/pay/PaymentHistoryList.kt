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
          val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
          sdf.format(paymentWithUser.payment.timestamp)
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