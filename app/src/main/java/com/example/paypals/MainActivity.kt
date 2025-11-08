package com.example.paypals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.paypals.ui.navigation.MainNavigationScreen
import com.example.paypals.ui.theme.PayPalsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContent {
               PayPalsTheme {
                    MainNavigationScreen()
               }

          }
     }
}

@Preview (showBackground =  true,
     device = Devices.PIXEL_5)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
     TopAppBar(
          title = { Text("PayPals") },
          navigationIcon = { Icon(painterResource(R.drawable.ic_pay), null) },
          actions = {
               Icon(imageVector = Icons.Default.Settings, null)
          },
          colors = TopAppBarDefaults.topAppBarColors(
               containerColor = MaterialTheme.colorScheme.tertiaryContainer,
               titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
               navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
               actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
          )
     )
}
