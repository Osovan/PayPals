package com.example.paypals.ui.screen.pay

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.paypals.domain.model.User


@Composable
fun PaymentCard(
     nextUser: User?,
     onPayClicked: (Double) -> Unit,
) {
     val screenWidth = LocalConfiguration.current.screenWidthDp.dp
     var amountText by rememberSaveable { mutableStateOf("3") }

     Log.d(" Oscar PaymentCard", "nextUser actualizado: ${nextUser?.name}")
     Card(
          modifier = Modifier
               .wrapContentHeight()
               .width(screenWidth * 0.9f),
          shape = RoundedCornerShape(16.dp),
          elevation = CardDefaults.cardElevation(4.dp),
          colors = CardDefaults.cardColors(
               containerColor = MaterialTheme.colorScheme.surfaceContainerLow
          )
     ) {
          Column(
               modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp),
               verticalArrangement = Arrangement.SpaceBetween
          ) {
               Row(
                    verticalAlignment = Alignment.CenterVertically
               ) {
                    val imageUrl = nextUser?.profileImageUri

                    if (!imageUrl.isNullOrBlank()) {
                         AsyncImage(
                              model = imageUrl,
                              contentDescription = "Foto del usuario",
                              modifier = Modifier
                                   .padding(16.dp)
                                   .size(64.dp)
                                   .clip(CircleShape)
                                   .background(Color.White),
                              contentScale = ContentScale.Crop
                         )
                    } else {
                         Icon(
                              imageVector = Icons.Default.Person,
                              contentDescription = "Usuario",
                              modifier = Modifier
                                   .padding(16.dp)
                                   .size(64.dp)
                                   .clip(CircleShape)
                                   .background(Color.White),
                              tint = MaterialTheme.colorScheme.primary
                         )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                         modifier = Modifier
                              .wrapContentWidth()
                              .padding(8.dp)
                    ) {
                         Text(
                              text = "Siguiente pago:",
                              style = MaterialTheme.typography.titleMedium,
                              fontSize = 12.sp
                         )
                         Text(
                              text = nextUser?.name ?: "Nadie",
                              style = MaterialTheme.typography.titleMedium,
                              fontSize = 24.sp
                         )
                    }
               }

               PaymentAmountTextField(
                    amountText = amountText,
                    onAmountChange = { amountText = it}
               )

               PaymentButton(
                    amountText = amountText,
                    onPayClicked = {
                         val amount = it.toDoubleOrNull() ?: 0.0
                         if (amount > 0) {
                              onPayClicked(amount)
                              amountText = "3" // reset
                         }
                    }
               )
          }
     }
}