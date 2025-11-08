package com.example.paypals.ui.screen.pay

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentAmountTextField(
     amountText: String,
     onAmountChange: (String) -> Unit,
) {
     Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
     ) {
          Text("Cantidad a pagar: ")

          OutlinedTextField(
               value = amountText,
               onValueChange = { newText ->
                    val filtered = newText.filter { it.isDigit() || it == '.' }
                    onAmountChange(filtered)
               },
               modifier = Modifier
                    .width(130.dp),
               textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
               singleLine = true,
               trailingIcon = {
                    Text("€", fontSize = 24.sp)
               },
               colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
               ),
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
          )
     }
}