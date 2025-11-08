package com.example.paypals.ui.screen.pay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.paypals.R

@Composable
fun PaymentButton(
     amountText: String,
     onPayClicked: (String) -> Unit,
) {
     Button(
          onClick = { onPayClicked(amountText) },
          modifier = Modifier
               .height(80.dp)
               .fillMaxWidth()
               .padding(top = 16.dp),
          shape = RoundedCornerShape(16.dp),
          colors = ButtonDefaults.buttonColors(
               containerColor = MaterialTheme.colorScheme.primary,
               contentColor = MaterialTheme.colorScheme.onPrimary
          )
     ) {
          Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center,
               modifier = Modifier.fillMaxWidth()
          ) {
               Icon(
                    painter = painterResource(R.drawable.ic_pay),
                    contentDescription = "Pagar",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
               )
               Spacer(modifier = Modifier.width(12.dp))
               Text(
                    text = "Pagar",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.labelLarge
               )
          }
     }
}